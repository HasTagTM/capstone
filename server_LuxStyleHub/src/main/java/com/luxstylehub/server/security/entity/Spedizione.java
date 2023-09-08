package com.luxstylehub.server.security.entity;

import java.time.LocalDate;

import com.luxstylehub.server.enumerated.MetodoSpedizione;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "spedizioni")
public class Spedizione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate dataSpedizione;
	
	@Column(nullable = false)
	private Double costoSpedizione;
	
	@Column(nullable = false)
	private MetodoSpedizione metodo;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "indirizzo_id")
	private Indirizzo indirizzo;
	
//	@OneToOne
//	private Ordine ordine;
//	
}
	

