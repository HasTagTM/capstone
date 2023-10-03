package com.luxstylehub.server.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luxstylehub.server.security.entity.Ordine;

@Repository
public interface OrdineRepository extends CrudRepository<Ordine, Long>{

}
