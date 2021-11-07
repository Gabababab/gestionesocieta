package it.prova.gestionesocieta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Progetto;


public interface ProgettoRepository extends CrudRepository<Progetto, Long>,QueryByExampleExecutor <Progetto> {

	@EntityGraph(attributePaths = { "impiegati" })
	Optional<Progetto> findByIdIs(Long id);
	
	@Query("SELECT DISTINCT p from Societa s join s.impiegati i join i.progetti p where s.id = ?1")
	List<Progetto> findAllByIdSocieta(Long id);
	
	List<Progetto> findAllDistinctByImpiegati_RalIs(int ral);
}
