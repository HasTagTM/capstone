package com.luxstylehub.server.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.luxstylehub.server.security.entity.Prodotto;

@Configuration
public class ProdottoConfiguration {

	@Bean("crea_prodotti")
	@Scope("prototype")
	public Prodotto creaProdotto() {
		return new Prodotto();
	}
}
