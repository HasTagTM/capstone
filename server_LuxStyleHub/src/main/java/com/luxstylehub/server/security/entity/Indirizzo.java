package com.luxstylehub.server.security.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
public class Indirizzo {
	 @Id
	 @SequenceGenerator(name = "seq3_id",sequenceName = "seq3_id", allocationSize = 1, initialValue = 1)
	 @GeneratedValue(generator = "seq3_id")	 private Long id;
	 
	 @CsvBindByName(column = "Città", required = true)
	 @CsvBindByPosition(position = 0)
	 private String città;
	 
	 @CsvBindByName(column = "Regione", required = true)
	 @CsvBindByPosition(position = 1)
	 private String regione;
	 
	 @CsvBindByName(column = "Territorio", required = true)
	 @CsvBindByPosition(position = 2)
	 private String territorio;
	 
	 @CsvBindByName(column = "Sigla", required = true)
	 @CsvBindByPosition(position = 3)
	 private String sigla;
	 
	 
}
