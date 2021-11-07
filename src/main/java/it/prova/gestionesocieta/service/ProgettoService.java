package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Progetto;


public interface ProgettoService {

	public List<Progetto> listAllProgetti() ;

	public Progetto caricaSingoloProgetto(Long id);

	public void aggiorna(Progetto edificioInstance);

	public void inserisciNuovo(Progetto edificioInstance);

	public void rimuovi(Progetto edificioInstance);

	public List<Progetto> findByExample(Progetto example);
}
