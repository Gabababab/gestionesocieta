package it.prova.gestionesocieta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocieta.model.Progetto;


public interface ProgettoRepository extends CrudRepository<Progetto, Long>,QueryByExampleExecutor <Progetto> {

}
