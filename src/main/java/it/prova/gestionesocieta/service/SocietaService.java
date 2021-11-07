package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Societa;


public interface SocietaService {

	public List<Societa> listAllSocieta() ;

	public Societa caricaSingoloSocieta(Long id);

	public void aggiorna(Societa edificioInstance);

	public void inserisciNuovo(Societa edificioInstance);

	public void rimuovi(Societa edificioInstance);

	public List<Societa> findByExample(Societa example);
}
