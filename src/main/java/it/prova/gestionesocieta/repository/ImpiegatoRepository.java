package it.prova.gestionesocieta.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Impiegato;


public interface ImpiegatoRepository extends CrudRepository<Impiegato, Long>,QueryByExampleExecutor <Impiegato> {

	//Eager
	@EntityGraph(attributePaths = { "progetti" })
	Optional<Impiegato> findByIdIs(Long id);
	
//	@Query(value="SELECT DISTINCT i from Societa s join s.impiegati i join i.progetti p where s.dataFondazione >= '1990/01/01' and p.durataInMesi >= 6 order by i.dataAssunzione DESC LIMIT 1", nativeQuery = true)
	@EntityGraph(attributePaths = { "societa", "progetti" })
	List<Impiegato> findBySocieta_DataFondazioneAfterAndProgetti_DurataInMesiGreaterThan(Date dateFondazione, Integer mesi);
}
