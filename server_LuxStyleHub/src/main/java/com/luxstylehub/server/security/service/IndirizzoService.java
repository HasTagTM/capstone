package com.luxstylehub.server.security.service;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.security.configuration.IndirizzoConfiguration;
import com.luxstylehub.server.security.entity.Indirizzo;
import com.luxstylehub.server.security.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	private Logger log = LoggerFactory.getLogger(IndirizzoService.class);
	
	@Autowired IndirizzoRepository db;
	@Autowired @Qualifier("crea_indirizzo") ObjectProvider<Indirizzo> indProv;
	
	public Indirizzo creaIndirizzo(String citta, String regione, String territorio, String sigla) {
		Indirizzo i = indProv.getObject().builder()
				.città(citta)
				.regione(regione)
				.territorio(territorio)
				.sigla(sigla)
				.build();
		db.save(i);
		System.out.println();
		log.info("indirizzo creato con successo e aggiunto al db" + i.getId());
		return i;
	}
	
	public Indirizzo add(Indirizzo andress) {
		return db.save(andress);
	}
	
	public List<Indirizzo> findRegione(String Regione){
		return db.findByRegioneIgnoreCase(Regione);
	}
	
	public List<Indirizzo> findRegioneandSigla(String Regione,String Sigla){
		return db.findByRegioneIgnoreCaseAndSiglaIgnoreCase(Regione,Sigla);
	}
	
	public Indirizzo findCity(String city) {
		return db.findByCittàIgnoreCase(city);
	}
	
	public List<Indirizzo> findAll(){
		return db.findAll();
	}
	public Indirizzo findCitta(String citta){
		return db.findByCittàIgnoreCase(citta);
	}
	
	public HashSet<String> findAllRegioni(){
		List<Indirizzo> list=db.findAll();
		HashSet<String> set=new HashSet<String>();
		for(Indirizzo l : list) {
			set.add(l.getRegione());
		}
		return set;
	}
	
	public HashSet<String> findAllTerritorio(String Regione){
		List<Indirizzo> list=db.findByRegioneIgnoreCase(Regione);
		HashSet<String> set=new HashSet<String>();
		for(Indirizzo l : list) {
			set.add(l.getTerritorio());
		}
		set.forEach(s-> System.out.println(s));
		return set;
	}
	
	public HashSet<String> findAllCitta(String Territorio){
		List<Indirizzo> list=db.findByTerritorioIgnoreCase(Territorio);
		HashSet<String> set=new HashSet<String>();
		for(Indirizzo l : list) {
			set.add(l.getCittà());
		}
		set.forEach(s-> System.out.println(s));
		return set;
	}

	public Indirizzo findbyId(Long id) {
		return db.findById(id).get();
	}
	
}
