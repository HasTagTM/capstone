package com.luxstylehub.server.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.security.entity.Recensione;
import com.luxstylehub.server.security.repository.RecensioneRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecensioneService {
	
	@Autowired RecensioneRepository recensSvc;

	public Recensione crea(Recensione f) {
		return recensSvc.save(f);
	}
	
	public Recensione getById(long id) {
		if(!recensSvc.existsById(id))
			throw new EntityNotFoundException("Impossibile trovare la recensione");
		return recensSvc.findById(id).get();
	}
	
	public Recensione aggiorna(long id, Recensione f) {
		if(recensSvc.existsById(id) || f.getId()!=id)
			throw new EntityNotFoundException("Impossibile trovare la recensione");
		return crea(f);
	}
	
	public String elimina(long id) {
		if(!recensSvc.existsById(id))
			throw new EntityNotFoundException("Impossibile la recensione");
		recensSvc.deleteById(id);
		return "Fattura eliminata con successo";
	}

	public List<Recensione> findAll(){
		List<Recensione> l = (List<Recensione>)recensSvc.findAll();
		return l;
	}

}
