package it.prova.gestionesocieta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Impiegato;


public interface ImpiegatoRepository extends CrudRepository<Impiegato, Long>,QueryByExampleExecutor <Impiegato> {

}
