package com.luxstylehub.server.security.entity;

import java.util.List;

import org.springframework.context.annotation.DependsOn;

import com.luxstylehub.server.enumerated.Categories;
import com.luxstylehub.server.enumerated.Genere;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "prodotti")
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descrizione;
	
	@Column(nullable = false)
	private Double prezzo;
	
	@Column(nullable = false, unique = true)
	private String immagineUrl;
	
	@Column(nullable = false)
	private Integer quantita;
	
	@Enumerated(EnumType.STRING)
	private Genere genere;
	
	@Enumerated(EnumType.STRING)
	private Categories categoria;
	
	private Boolean preferito;


}
