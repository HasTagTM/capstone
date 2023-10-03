package com.luxstylehub.server.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.luxstylehub.server.security.entity.Pagamento;

@Configuration
public class PagamentoConfiguration {

	@Bean("crea_pagamento")
	@Scope("prototype")
	public Pagamento creaPagamento() {
		return new Pagamento();
	}
}
