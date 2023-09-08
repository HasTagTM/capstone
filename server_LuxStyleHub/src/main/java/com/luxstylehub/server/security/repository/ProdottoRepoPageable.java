package com.luxstylehub.server.security.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.luxstylehub.server.security.entity.Prodotto;

public interface ProdottoRepoPageable extends PagingAndSortingRepository<Prodotto, Long>{

}
