package com.luxstylehub.server.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.luxstylehub.server.security.entity.Indirizzo;

@Configuration
public class IndirizzoConfiguration {

	@Bean("crea_indirizzo")
	@Scope("prototype")
	public Indirizzo creaIndirizzo() {
		return new Indirizzo();
	}
	
}
