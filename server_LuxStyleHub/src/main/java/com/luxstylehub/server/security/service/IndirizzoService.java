package com.luxstylehub.server.security.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxstylehub.server.security.entity.Indirizzo;
import com.luxstylehub.server.security.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired IndirizzoRepository db;
	
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
