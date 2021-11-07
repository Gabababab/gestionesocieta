package it.prova.gestionesocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Progetto;
import it.prova.gestionesocieta.repository.ProgettoRepository;

@Service
public class ProgettoServiceImpl implements ProgettoService{

	@Autowired
	private ProgettoRepository progettoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Progetto> listAllProgetti() {
		return (List<Progetto>) progettoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Progetto caricaSingoloProgetto(Long id) {
		return progettoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);
	}

	@Transactional
	public void inserisciNuovo(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);
	}

	@Transactional
	public void rimuovi(Progetto progettoInstance) {
		progettoRepository.delete(progettoInstance);
	}

	@Override
	public List<Progetto> findByExample(Progetto example) {
		// TODO Auto-generated method stub
		return null;
	}
}
