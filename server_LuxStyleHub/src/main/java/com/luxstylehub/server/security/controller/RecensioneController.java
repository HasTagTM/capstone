package com.luxstylehub.server.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.luxstylehub.server.security.entity.Recensione;

import com.luxstylehub.server.security.service.RecensioneService;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

@Autowired RecensioneService recSvc;
	
	@PostMapping
    public ResponseEntity<?> crea(@RequestBody Recensione f) {
        return new ResponseEntity<Recensione>(recSvc.crea(f), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<Recensione>(recSvc.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> modifica(@PathVariable long id, @RequestBody Recensione f) {
        return new ResponseEntity<Recensione>(recSvc.aggiorna(id, f), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable long id) {
        return new ResponseEntity<String>(recSvc.elimina(id), HttpStatus.OK);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Recensione>> getAll() {
    	return new ResponseEntity<List<Recensione>>(recSvc.findAll(), HttpStatus.OK);
    }
	
}
