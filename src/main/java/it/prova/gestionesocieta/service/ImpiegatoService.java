package it.prova.gestionesocieta.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.prova.gestionesocieta.model.Impiegato;


public interface ImpiegatoService {

	public List<Impiegato> listAllImpiegati() ;

	public Impiegato caricaSingoloImpiegato(Long id);
	
	public Impiegato caricaSingoloImpiegatoEagerProgetti(Long id);

	public void aggiorna(Impiegato impiegatoInstance);

	public void inserisciNuovo(Impiegato impiegatoInstance);

	public void rimuovi(Impiegato impiegatoInstance);

	public List<Impiegato> findByExample(Impiegato example);
	
	public List<Impiegato> findImpiegatoPiuAnzianoDiSocietaFondatePrimaDiCheLavoraAProgettoCheDuraPiuDi(Date date, Integer mesi);
}
