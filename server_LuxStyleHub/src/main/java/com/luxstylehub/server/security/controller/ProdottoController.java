package com.luxstylehub.server.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxstylehub.server.enumerated.Categories;
import com.luxstylehub.server.security.entity.Prodotto;
import com.luxstylehub.server.security.service.ProdottoService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600, allowedHeaders = "*")
@RequestMapping("/api/prodotti")
public class ProdottoController {

	@Autowired ProdottoService prodSvc;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> crea(@RequestBody Prodotto f) {
        return new ResponseEntity<Prodotto>(prodSvc.crea(f), HttpStatus.OK);
    }
	

    @GetMapping("/{id}")
//    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return new ResponseEntity<Prodotto>(prodSvc.getById(id), HttpStatus.OK);
    }
    
    @GetMapping("/categories")
    public ResponseEntity<List<?>> getCategoriess(@RequestParam("categoria") String categoriaStr) {
        Categories categoria = Categories.valueOf(categoriaStr.toUpperCase());
        List<Prodotto> list = prodSvc.findByCategoria(categoria);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> modifica(@PathVariable long id, @RequestBody Prodotto f) {
        return new ResponseEntity<Prodotto>(prodSvc.aggiorna(id, f), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> elimina(@PathVariable long id) {
        prodSvc.elimina(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Prodotto>> getAll() {
    	List<Prodotto> list = prodSvc.getAllContacts();
    	return new ResponseEntity<List<Prodotto>>(list, HttpStatus.OK);
    }
    
    
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Page<Prodotto>> getAll(Pageable pageable) {
        // http://localhost:8080/api/contacts/pageable?page=0&size=10&sort=age,ASC
        // ?page=0&size=10&sort=age,ASC -> sono i parametri che saranno contenuti nell'ogg di tipo Pageable
    	return new ResponseEntity<Page<Prodotto>>(prodSvc.getAllContactsPageable(pageable), HttpStatus.OK);
    }
}
