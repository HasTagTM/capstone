package com.luxstylehub.server.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.enumerated.MetodoPagamento;
import com.luxstylehub.server.enumerated.StatoPagamento;
import com.luxstylehub.server.security.entity.Ordine;
import com.luxstylehub.server.security.entity.Pagamento;
import com.luxstylehub.server.security.repository.PagamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {
	
	private Logger log = LoggerFactory.getLogger(PagamentoService.class);
	
	@Autowired PagamentoRepository pagamentoRepo;
	@Autowired @Qualifier("crea_pagamento") private ObjectProvider<Pagamento> provider;
	
	public Pagamento creaIndirizzo(MetodoPagamento metodoPagamento, StatoPagamento stato, String titolare, String numeroCartaCredito, String dataScadenzaCartaCredito, String codiceCvvCartaCredito, Ordine ordine) {
		Pagamento p = provider.getObject().builder()
				.metodoPagamento(metodoPagamento)
				.stato(stato)
				.titolare(titolare)
				.numeroCartaCredito(numeroCartaCredito)
				.dataScadenzaCartaCredito(dataScadenzaCartaCredito)
				.codiceCvvCartaCredito(codiceCvvCartaCredito)
				.ordine(ordine)
				.build();
		pagamentoRepo.save(p);
		System.out.println();
		log.info("pagamento creato con successo e aggiunto al db" + p.getId());
		return p;
	}

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
