package it.prova.gestionesocieta.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Impiegato;
import it.prova.gestionesocieta.model.Progetto;
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
		if (societaService.caricaSingoloSocieta(daRimuovere.getId()) != null)
			throw new RuntimeException("testRimozioneSocieta FALLITO: rimozione fallita");

		System.out.println("testRimozioneSocieta: SUCCESSO");
	}

	public void testInserimentoImpiegato() {

		Societa societa = new Societa("FSociety", new Date());
		if (societa.getId() != null)
			throw new RuntimeException("testInserimentoImpiegato FALLITO: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testInserimentoImpiegato FALLITO: inserimento fallito");

		Impiegato impiegato = new Impiegato("Mario", "Verdi", 15000, new Date());
		if (impiegato.getId() != null)
			throw new RuntimeException("testInserimentoImpiegato FALLITO: transient object con id valorizzato");

		impiegato.setSocieta(societa);

		impiegatoService.inserisciNuovo(impiegato);

		if (impiegato.getId() == null)
			throw new RuntimeException("testInserimentoImpiegato FALLITO:inserimento fallito");

		System.out.println("testInserimentoImpiegato: SUCCESSO");
	}

	public void testInserimentoProgetto() {

		Progetto progetto = new Progetto("Progetto", "Bayer", 6);
		if (progetto.getId() != null)
			throw new RuntimeException("testInserimentoProgetto FALLITO: transient object con id valorizzato");

		progettoService.inserisciNuovo(progetto);

		if (progetto.getId() == null)
			throw new RuntimeException("testInserimentoProgetto FALLITO: inserimento fallito");

		System.out.println("testInserimentoProgetto: SUCCESSO");
	}

	public void testCollegamentoImpiegatoAProgetti() {

		Societa societa = new Societa("Dell", new Date());
		if (societa.getId() != null)
			throw new RuntimeException(
					"testCollegamentoImpiegatoAProgetti FALLITO: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		Progetto progetto1 = new Progetto("progetto1", "Dell", 2);
		Progetto progetto2 = new Progetto("progetto2", "Dell", 5);
		if (progetto1.getId() != null || progetto2.getId() != null)
			throw new RuntimeException(
					"testCollegamentoImpiegatoAProgetti FALLITO: transient object con id valorizzato");

		progettoService.inserisciNuovo(progetto1);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegatoDaCollegare = new Impiegato("Federico", "Barbarossa", 2000, new Date());
		if (impiegatoDaCollegare.getId() != null)
			throw new RuntimeException(
					"testCollegamentoImpiegatoAProgetti FALLITO: transient object con id valorizzato");

		impiegatoDaCollegare.setSocieta(societa);

		impiegatoService.inserisciNuovo(impiegatoDaCollegare);

		impiegatoDaCollegare.addToProgetti(progetto1);
		impiegatoDaCollegare.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegatoDaCollegare);

		Impiegato reloaded = impiegatoService.caricaSingoloImpiegatoEagerProgetti(impiegatoDaCollegare.getId());
		if (reloaded.getProgetti().size() != 2)
			throw new RuntimeException("testCollegamentoImpiegatoAProgetti FALLITO: collegamento a progetti fallito");

		System.out.println("testCollegamentoImpiegatoAProgettis: SUCCESSO");
	}

	public void testCollegamentoProgettoAImpiegati() {

		Societa societa = new Societa("Avon", new Date());
		if (societa.getId() != null)
			throw new RuntimeException(
					"testCollegamentoProgettoAImpiegati FALLITO: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		Progetto progettoDaCollegare = new Progetto("progetto1", "Dell", 2);
		if (progettoDaCollegare.getId() != null)
			throw new RuntimeException(
					"testCollegamentoProgettoAImpiegati FALLITO: transient object con id valorizzato");

		progettoService.inserisciNuovo(progettoDaCollegare);

		Impiegato impiegato1 = new Impiegato("Federico", "Barbarossa", 2000, new Date());
		Impiegato impiegato2 = new Impiegato("Alberto", "Barbarossa", 2090, new Date());
		if (impiegato1.getId() != null || impiegato2.getId() != null)
			throw new RuntimeException(
					"testCollegamentoProgettoAImpiegati FALLITO: transient object con id valorizzato");

		impiegato1.setSocieta(societa);
		impiegato2.setSocieta(societa);

		impiegatoService.inserisciNuovo(impiegato1);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato1.addToProgetti(progettoDaCollegare);
		impiegato2.addToProgetti(progettoDaCollegare);

		impiegatoService.aggiorna(impiegato1);
		impiegatoService.aggiorna(impiegato2);

		Progetto reloaded = progettoService.caricaProgettoEager(progettoDaCollegare.getId());
		if (reloaded.getImpiegati().size() != 2)
			throw new RuntimeException("testCollegamentoProgettoAImpiegati FALLITO: collegamento a progetti fallito");

		System.out.println("testCollegamentoProgettoAImpiegati: SUCCESSO");
	}

	public void testRicercaListaClientiDeiProgettiDiUnaSocieta() {

		Long nowInMillisecondi = new Date().getTime();
		
		Societa societa = new Societa("Societa"+nowInMillisecondi, new Date());
		if (societa.getId() != null)
			throw new RuntimeException(
					"testRicercaListaClientiDeiProgettiDiUnaSocieta FALLITO: transient object con id valorizzato");
		
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testRicercaListaClientiDeiProgettiDiUnaSocieta FALLITO: inserimento societa fallito");

		Progetto progetto1 = new Progetto("Progetto"+nowInMillisecondi, "Apple", 3);
		Progetto progetto2 = new Progetto("Progetto"+nowInMillisecondi, "Apple", 3);
		Progetto progetto3 = new Progetto("Progetto"+nowInMillisecondi, "Microsoft", 2);
		if (progetto1.getId() != null || progetto2.getId() != null || progetto3.getId() != null)
			throw new RuntimeException(
					"testRicercaListaClientiDeiProgettiDiUnaSocieta FALLITO: transient object con id valorizzato");
		
		progettoService.inserisciNuovo(progetto1);
		progettoService.inserisciNuovo(progetto2);
		progettoService.inserisciNuovo(progetto3);

		Impiegato impiegato = new Impiegato("Aldo", "Baglio", 500, new Date());
		if (impiegato.getId() != null)
			throw new RuntimeException(
					"testRicercaListaClientiDeiProgettiDiUnaSocieta FALLITO: transient object con id valorizzato");
		impiegato.setSocieta(societa);
		impiegatoService.inserisciNuovo(impiegato);

		impiegato.addToProgetti(progetto1);
		impiegato.addToProgetti(progetto2);
		impiegato.addToProgetti(progetto3);

		impiegatoService.aggiorna(impiegato);

		List<Progetto> risultatoRicerca = progettoService.caricaProgettiDaIdSocieta(societa.getId());

		if (risultatoRicerca.size() != 3)
			throw new RuntimeException("testRicercaListaClientiDeiProgettiDiUnaSocieta FALLITO: numero inatteso");

		System.out.println("testRicercaListaClientiDeiProgettiDiUnaSocieta: SUCCESSO");
	}
	
	public void testRicercaSocietaConProgettiCheDuranoPiuDiUnAnno() {
		Long nowInMillisecondi = new Date().getTime();
		
		Societa societa = new Societa("Societa"+nowInMillisecondi, new Date());
		if (societa.getId() != null)
			throw new RuntimeException(
					"testRicercaSocietaConProgettiCheDuranoPiuDiUnAnno FALLITO: transient object con id valorizzato");
		
		societaService.inserisciNuovo(societa);

		if (societa.getId() == null)
			throw new RuntimeException("testRicercaSocietaConProgettiCheDuranoPiuDiUnAnno FALLITO: inserimento societa fallito");

		Progetto progetto = new Progetto("Progetto"+nowInMillisecondi, "Apple", 14);
		if (progetto.getId() != null)
			throw new RuntimeException(
					"testRicercaSocietaConProgettiCheDuranoPiuDiUnAnno FALLITO: transient object con id valorizzato");
		
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato = new Impiegato("Francesco", "Baglio", 500, new Date());
		if (impiegato.getId() != null)
			throw new RuntimeException(
					"testRicercaSocietaConProgettiCheDuranoPiuDiUnAnno FALLITO: transient object con id valorizzato");
		impiegato.setSocieta(societa);
		impiegatoService.inserisciNuovo(impiegato);

		impiegato.addToProgetti(progetto);

		impiegatoService.aggiorna(impiegato);

		List<Societa> societaRisultato = societaService.findAllSocietaConDurataProgettiPiuDiUnAnno();

		if (societaRisultato.size() != 1)
			throw new RuntimeException("testRicercaSocietaConProgettiCheDuranoPiuDiUnAnno FALLITO: numero inatteso");

		System.out.println("testRicercaSocietaConProgettiCheDuranoPiuDiUnAnno: SUCCESSO");
	}
	
	public void testRicercaImpiegatiDaRAL() {
		
		Societa societa = new Societa("Activision", new Date());
		if (societa.getId() != null)
			throw new RuntimeException(
					"testRicercaImpiegatiDaRAL FALLITO: transient object con id valorizzato");
		societaService.inserisciNuovo(societa);

		Progetto progetto1 = new Progetto("Progetto1", "Activision", 10);
		Progetto progetto2 = new Progetto("Progetto2", "Activision", 13);
		if (progetto1.getId() != null || progetto2.getId() != null)
			throw new RuntimeException(
					"testRicercaImpiegatiDaRAL FALLITO: transient object con id valorizzato");
		
		progettoService.inserisciNuovo(progetto1);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato1 = new Impiegato("Verde", "Rossi", 30000, new Date());
		Impiegato impiegato2 = new Impiegato("Alessio", "Verdi", 100, new Date());
		if (impiegato1.getId() != null || impiegato2.getId() != null)
			throw new RuntimeException(
					"testRicercaImpiegatiDaRAL FALLITO: transient object con id valorizzato");
		impiegato1.setSocieta(societa);
		impiegato2.setSocieta(societa);
		
		impiegatoService.inserisciNuovo(impiegato2);
		impiegatoService.inserisciNuovo(impiegato1);

		impiegato1.addToProgetti(progetto1);
		impiegato2.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato1);
		impiegatoService.aggiorna(impiegato2);

		List<Progetto> progetti = progettoService.findAllImpiegatoConRadAlmenoA(30000);
		if (progetti.size() != 1)
			throw new RuntimeException("testRicercaImpiegatiDaRAL FALLITO: numero inatteso");

		System.out.println("testRicercaImpiegatiDaRAL: SUCCESSO");
	}
	
	public void testImpiegatoPiuAnzianoDiSocietaFondatePrimaDiCheLavoraAProgettoCheDuraPiuDi() {

		Societa societa = new Societa("Apple", new Date("1950/02/26"));
		if (societa.getId() != null)
			throw new RuntimeException(
					"testRicercaImpiegatiDaRAL FALLITO: transient object con id valorizzato");

		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("Nuovo progetto", "apple", 25);
		if (progetto.getId() != null )
			throw new RuntimeException(
					"testRicercaImpiegatiDaRAL FALLITO: transient object con id valorizzato");
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato1 = new Impiegato("Guglielmo", "Rossi", 30000, new Date("1987/01/04"));
		Impiegato impiegato2 = new Impiegato("Lucia", "Rossi", 20000, new Date("2000/01/04"));
		if (impiegato1.getId() != null || impiegato2.getId() != null)
			throw new RuntimeException(
					"testRicercaImpiegatiDaRAL FALLITO: transient object con id valorizzato");
		
		impiegato1.setSocieta(societa);
		impiegato2.setSocieta(societa);
		
		impiegatoService.inserisciNuovo(impiegato2);
		impiegatoService.inserisciNuovo(impiegato1);

		impiegato1.addToProgetti(progetto);
		impiegato2.addToProgetti(progetto);

		impiegatoService.aggiorna(impiegato1);
		impiegatoService.aggiorna(impiegato2);

		System.out.println(impiegatoService
				.findImpiegatoPiuAnzianoDiSocietaFondatePrimaDiCheLavoraAProgettoCheDuraPiuDi(new Date("1960/01/01"), 6));
		
		System.out.println("testImpiegatoPiuAnzianoDiSocietaFondatePrimaCheLavoraAProgettoDiPiuDi ok...............");
	}
	
}
