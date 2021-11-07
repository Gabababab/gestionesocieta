package it.prova.gestionesocieta.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Impiegato;
import it.prova.gestionesocieta.repository.ImpiegatoRepository;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {

	@Autowired
	private ImpiegatoRepository impiegatoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Impiegato> listAllImpiegati() {
		return (List<Impiegato>) impiegatoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Impiegato caricaSingoloImpiegato(Long id) {
		return impiegatoRepository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Impiegato caricaSingoloImpiegatoEagerProgetti(Long id) {
		return impiegatoRepository.findByIdIs(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
	}

	@Transactional
	public void inserisciNuovo(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
	}

	@Transactional
	public void rimuovi(Impiegato impiegatoInstance) {
		impiegatoRepository.delete(impiegatoInstance);
	}

	@Override
	public List<Impiegato> findByExample(Impiegato example) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	public List<Impiegato> findImpiegatoPiuAnzianoDiSocietaFondatePrimaDiCheLavoraAProgettoCheDuraPiuDi(Date date, Integer mesi) {
		return impiegatoRepository.findBySocieta_DataFondazioneAfterAndProgetti_DurataInMesiGreaterThanOrderByDataAssunzioneDescFirst(date, mesi);
	}
}
