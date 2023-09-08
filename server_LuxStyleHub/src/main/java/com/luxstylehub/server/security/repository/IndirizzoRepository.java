package com.luxstylehub.server.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxstylehub.server.security.entity.Indirizzo;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long>{
	
	public List<Indirizzo> findByRegioneIgnoreCase(String Regione);
	public List<Indirizzo> findByTerritorioIgnoreCase(String Territorio);
	public List<Indirizzo> findByRegioneIgnoreCaseAndSiglaIgnoreCase(String Regione,String Sigla);
	public Indirizzo findByCittàIgnoreCase(String citta);
}

