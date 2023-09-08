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

import com.luxstylehub.server.security.entity.Pagamento;
import com.luxstylehub.server.security.service.PagamentoService;

@RestController
@RequestMapping("/pagamenti")
public class PagamentoController {
	
	@Autowired PagamentoService pagamSvc;

	@PostMapping
    public ResponseEntity<?> crea(@RequestBody Pagamento f) {
        return new ResponseEntity<Pagamento>(pagamSvc.crea(f), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<Pagamento>(pagamSvc.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> modifica(@PathVariable long id, @RequestBody Pagamento f) {
        return new ResponseEntity<Pagamento>(pagamSvc.aggiorna(id, f), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable long id) {
        return new ResponseEntity<String>(pagamSvc.elimina(id), HttpStatus.OK);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Pagamento>> getAll() {
    	return new ResponseEntity<List<Pagamento>>(pagamSvc.findAll(), HttpStatus.OK);
    }
	
	
}
