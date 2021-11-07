package it.prova.gestionesocieta.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private ImpiegatoService impiegatoService;

	@Autowired
	private ProgettoService progettoService;

	@Autowired
	private SocietaService societaService;

	public void testInserimentoSocieta() {

		Societa daInserire = new Societa("Apple", new Date());
		if (daInserire.getId() != null)
			throw new RuntimeException("testInserimentoSocieta FALLITO: transient object con id valorizzato");
		
		societaService.inserisciNuovo(daInserire);

		if (daInserire.getId() == null)
			throw new RuntimeException("testInserimentoSocieta FALLITO: inserimento fallito");
		
		System.out.println("testInserimentoSocieta: SUCCESSO");
	}
	
	public void testFindByExampleSocieta() {
		
		Long nowInMillisecondi = new Date().getTime();
		
		Societa societa = new Societa("Societa" + nowInMillisecondi, new Date("2005/12/11"));
		Societa societa2 = new Societa("Societa" + nowInMillisecondi, new Date("1990/02/11"));
		Societa societa3 = new Societa("Societa" + nowInMillisecondi, new Date("1850/04/11"));

		if (societa.getId() != null || societa2.getId() != null || societa3.getId() != null)
			throw new RuntimeException("testFindByExampleSocieta FALLITO: transient object con id valorizzato");
		
		societaService.inserisciNuovo(societa);
		societaService.inserisciNuovo(societa2);
		societaService.inserisciNuovo(societa3);

		if (societa.getId() == null || societa2.getId() == null || societa3.getId() == null)
			throw new RuntimeException("testFindByExampleSocieta FALLITO: inserimento fallito");

		Societa example = new Societa("societa", null);

		if (societaService.findByExample(example).size() != 3)
			throw new RuntimeException("testFindByExampleSocieta FALLITO: risultato inatteso");

		System.out.println("testFindByExampleSocieta: SUCCESSO");
	}
	
	public void testRimozioneSocieta() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa daRimuovere = new Societa("Societa" + nowInMillisecondi, new Date("2005/12/11"));
		if (daRimuovere.getId() != null)
			throw new RuntimeException("testRimozioneSocieta FALLITO: transient object con id valorizzato");
		
		societaService.inserisciNuovo(daRimuovere);
		if (daRimuovere.getId() == null)
			throw new RuntimeException("testRimozioneSocieta FALLITO: inserimento fallito");
		
		societaService.rimuovi(daRimuovere);
		if(societaService.caricaSingoloSocieta(daRimuovere.getId()) != null)
			throw new RuntimeException("testRimozioneSocieta FALLITO: rimozione fallita");
		
		System.out.println("testRimozioneSocieta: SUCCESSO");
	}
	
}
