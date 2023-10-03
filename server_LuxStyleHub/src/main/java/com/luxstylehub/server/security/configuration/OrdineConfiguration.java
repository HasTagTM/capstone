package com.luxstylehub.server.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.luxstylehub.server.security.entity.Ordine;

@Configuration
public class OrdineConfiguration {

	@Bean("crea_ordine")
	@Scope("prototype")
	public Ordine creaOrdine() {
		return new Ordine();
	}
	
}
