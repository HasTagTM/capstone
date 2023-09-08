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

import com.luxstylehub.server.security.entity.Ordine;
import com.luxstylehub.server.security.service.OrdineService;

@RestController
@RequestMapping("/ordini")
public class OrdineController {


@Autowired OrdineService ordSvc;
	
	@PostMapping
    public ResponseEntity<?> crea(@RequestBody Ordine f) {
        return new ResponseEntity<Ordine>(ordSvc.crea(f), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<Ordine>(ordSvc.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> modifica(@PathVariable long id, @RequestBody Ordine f) {
        return new ResponseEntity<Ordine>(ordSvc.aggiorna(id, f), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable long id) {
        return new ResponseEntity<String>(ordSvc.elimina(id), HttpStatus.OK);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Ordine>> getAll() {
    	return new ResponseEntity<List<Ordine>>(ordSvc.findAll(), HttpStatus.OK);
    }
	
}
