package com.luxstylehub.server.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.security.entity.Pagamento;
import com.luxstylehub.server.security.entity.Recensione;
import com.luxstylehub.server.security.repository.PagamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {
	
	@Autowired PagamentoRepository pagamentoRepo;

	public Pagamento crea(Pagamento f) {
		return pagamentoRepo.save(f);
	}
	
	public Pagamento getById(long id) {
		if(!pagamentoRepo.existsById(id))
			throw new EntityNotFoundException("Impossibile trovare il pagamento");
		return pagamentoRepo.findById(id).get();
	}
	
	public Pagamento aggiorna(long id, Pagamento f) {
		if(pagamentoRepo.existsById(id) || f.getId()!=id)
			throw new EntityNotFoundException("Impossibile trovare il pagamento");
		return crea(f);
	}
	
	public String elimina(long id) {
		if(!pagamentoRepo.existsById(id))
			throw new EntityNotFoundException("Impossibile il pagamento");
		pagamentoRepo.deleteById(id);
		return "Fattura eliminata con successo";
	}

	public List<Pagamento> findAll(){
		List<Pagamento> l = (List<Pagamento>)pagamentoRepo.findAll();
		return l;
	}

}
