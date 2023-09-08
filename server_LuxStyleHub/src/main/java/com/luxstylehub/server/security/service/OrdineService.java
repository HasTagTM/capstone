package com.luxstylehub.server.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.security.entity.Ordine;
import com.luxstylehub.server.security.entity.Recensione;
import com.luxstylehub.server.security.repository.OrdineRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdineService {

	@Autowired OrdineRepository ordineRepo;
	
	public Ordine crea(Ordine f) {
		return ordineRepo.save(f);
	}
	
	public Ordine getById(long id) {
		if(!ordineRepo.existsById(id))
			throw new EntityNotFoundException("Impossibile trovare l ordine");
		return ordineRepo.findById(id).get();
	}
	
	public Ordine aggiorna(long id, Ordine f) {
		if(ordineRepo.existsById(id) || f.getId()!=id)
			throw new EntityNotFoundException("Impossibile trovare l ordine");
		return crea(f);
	}
	
	public String elimina(long id) {
		if(!ordineRepo.existsById(id))
			throw new EntityNotFoundException("Impossibile l ordine");
		ordineRepo.deleteById(id);
		return "Fattura eliminata con successo";
	}

	public List<Ordine> findAll(){
		List<Ordine> l = (List<Ordine>)ordineRepo.findAll();
		return l;
	}
}
