package com.luxstylehub.server.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luxstylehub.server.enumerated.Categories;
import com.luxstylehub.server.security.entity.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long>{


	public List<Prodotto> findByCategoria(Categories categoria);
	
}
