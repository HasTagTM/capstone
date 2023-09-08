package com.luxstylehub.server.security.entity;

import com.luxstylehub.server.enumerated.MetodoPagamento;
import com.luxstylehub.server.enumerated.StatoPagamento;

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
import jakarta.persistence.ManyToOne;
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
@Table(name = "pagamenti")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoPagamento stato;
    
    @Column(nullable = false)
    private String titolare;
    
    @Column(nullable = false)
    private String numeroCartaCredito;
    
    @Column(nullable = false)
    private String dataScadenzaCartaCredito;
    
    @Column(nullable = false)
    private String codiceCvvCartaCredito;
   
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;
    
    public double gtOrdineTotale() {
    	return this.ordine.getTotale();
    }
	
}
