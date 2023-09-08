package com.luxstylehub.server.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.security.entity.Prodotto;
import com.luxstylehub.server.security.repository.ProdottoRepoPageable;
import com.luxstylehub.server.security.repository.ProdottoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdottoService {
	
	@Autowired ProdottoRepository prodottoRepo;
	@Autowired ProdottoRepoPageable prodottoPageable;

	
	public Prodotto crea(Prodotto f) {
		return prodottoRepo.save(f);
	}
	
	public Prodotto getById(long id) {
		if(!prodottoRepo.existsById(id))
			throw new EntityNotFoundException("Impossibile trovare il prodotto");
		return prodottoRepo.findById(id).get();
	}
	
	public Prodotto aggiorna(long id, Prodotto f) {
		if(prodottoRepo.existsById(id) || f.getId()!=id)
			throw new EntityNotFoundException("Impossibile trovare il prodotto");
		return crea(f);
	}
	
	public String elimina(long id) {
		if(!prodottoRepo.existsById(id))
			throw new EntityNotFoundException("Impossibile trovare il prodotto");
		prodottoRepo.deleteById(id);
		return "Fattura eliminata con successo";
	}
	
	public Page<Prodotto> getAllContactsPageable(Pageable pageable) {
        return prodottoPageable.findAll(pageable);
    }
}
