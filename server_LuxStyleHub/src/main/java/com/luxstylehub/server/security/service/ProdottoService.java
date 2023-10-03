package com.luxstylehub.server.security.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.enumerated.Categories;
import com.luxstylehub.server.enumerated.Genere;
import com.luxstylehub.server.security.entity.Pagamento;
import com.luxstylehub.server.security.entity.Prodotto;
import com.luxstylehub.server.security.entity.User;
import com.luxstylehub.server.security.repository.ProdottoRepoPageable;
import com.luxstylehub.server.security.repository.ProdottoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdottoService {
	
	private Logger log = LoggerFactory.getLogger(ProdottoService.class);
	
	@Autowired ProdottoRepoPageable prodottoPageable;
	@Autowired ProdottoRepository prodottoRepo;
	@Autowired @Qualifier("crea_prodotto") private ObjectProvider<Prodotto> provider;
	
	public Prodotto creaIndirizzo(String nome, Double prezzo, String immagineUrl, Integer quantita, Genere genere, Categories categoria, Boolean preferito) {
		Prodotto r = provider.getObject().builder()
				.nome(nome)
				.prezzo(prezzo)
				.immagineUrl(immagineUrl)
				.quantita(quantita)
				.genere(genere)
				.categoria(categoria)
				.preferito(preferito)

				.build();
		prodottoRepo.save(r);
		System.out.println();
		log.info("recensione creata con successo e aggiunto al db" + r.getId());
		return r;
	}


	
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

	public List<Prodotto> getAllContacts() {
		List<Prodotto> l = (List<Prodotto>)prodottoRepo.findAll();
		return l;
    }
	
	public Page<Prodotto> getAllContactsPageable(Pageable pageable) {
        return prodottoPageable.findAll(pageable);
    }
}
