package com.luxstylehub.server.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.security.entity.Indirizzo;
import com.luxstylehub.server.security.entity.Ordine;
import com.luxstylehub.server.security.entity.Prodotto;
import com.luxstylehub.server.security.entity.Spedizione;
import com.luxstylehub.server.security.repository.OrdineRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrdineService {

	private Logger log = LoggerFactory.getLogger(OrdineService.class);
	
	@Autowired OrdineRepository ordineRepo;
	@Autowired @Qualifier("crea_ordine") private ObjectProvider<Ordine> provider;
	
	public Ordine creaIndirizzo(String data, Double totale, List<Prodotto> prodottiordinati, Spedizione spedizione) {
		Ordine i = provider.getObject().builder()
				.data(data)
				.totale(totale)
				.prodottiOrdinati(prodottiordinati)
				.spedizione(spedizione)
				.build();
		ordineRepo.save(i);
		System.out.println();
		log.info("indirizzo creato con successo e aggiunto al db" + i.getId());
		return i;
	}
	
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
