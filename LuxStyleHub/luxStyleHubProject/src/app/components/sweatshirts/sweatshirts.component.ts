import { transition, trigger, useAnimation } from '@angular/animations';
import { Component } from '@angular/core';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Categories } from 'src/app/enumerated/categories';
import { Genere } from 'src/app/enumerated/genere';
import { Iprodotto } from 'src/app/interfaces/iprodotto';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
  selector: 'app-sweatshirts',
  templateUrl: './sweatshirts.component.html',
  styleUrls: ['./sweatshirts.component.css'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        useAnimation(fadeInAnimation) // fadeInAnimation è l'animazione definita nel passaggio 1 per l'entrata
      ]),
      transition(':leave', [
        useAnimation(fadeOutAnimation) // fadeOutAnimation è l'animazione definita per l'uscita
      ])
    ])
  ]
})
export class SweatshirtsComponent {
  carrelloAperto = false;
  carrello: Iprodotto[] = [];
  shoes: Iprodotto = {
    id: 0,
    nome: '',
    prezzo: 0,
    descrizione: '',
    immagineUrl: '',
    quantita: 0,
    genere: Genere.UNISEX,
    categoria: Categories.JEANS,
    recensione: '',
    preferito: false
  };

  prodotti: Iprodotto[] = [];

  constructor(private prodSvc: ProdottoService) {}
  showPage = false;


  ngOnInit() {
    this.showPage = true;
    this.getShoes();
  }
  getShoes() {
    this.prodSvc.getCategories("FELPE").subscribe((data) => {
      this.prodotti = data;
    });
  }

  aggiungiAiPreferiti(prodotto: Iprodotto) {
    // Chiamata al servizio per aggiungere il prodotto ai preferiti
    prodotto.preferito = !prodotto.preferito;
    this.prodSvc.aggiungiAiPreferiti(prodotto.id).subscribe(() => {
      console.log('Prodotto aggiunto ai Preferiti:', prodotto.nome);
    });
  }

  rimuoviDaPreferiti(prodotto: Iprodotto) {
    // Chiamata al servizio per rimuovere il prodotto dai preferiti
    prodotto.preferito = !prodotto.preferito;
    this.prodSvc.rimuoviDaPreferiti(prodotto.id).subscribe(() => {
      console.log('Prodotto rimosso dai Preferiti:', prodotto.nome);
    });
  }


  aggiungiAlCarrello(prodotto: Iprodotto) {
    this.carrello.push(prodotto);
  }

  rimuoviDalCarrello(prodotto: Iprodotto) {
    const index = this.carrello.indexOf(prodotto);
    if (index !== -1) {
      this.carrello.splice(index, 1);
    }
  }

  // aggiungiAlCarrello(prodotto: Iprodotto) {
  //   this.carrelloSvc.aggiungiAlCarrello(prodotto).subscribe((carrello) => {
  //     this.carrello = carrello.prodotti;
  //     // Gestisci la risposta o aggiorna il tuo carrello locale se necessario
  //   });
  // }

  // rimuoviDalCarrello(prodotto: Iprodotto) {
  //   this.carrelloSvc.rimuoviDalCarrello(prodotto).subscribe((carrello) => {
  //     this.carrello = carrello.prodotti;
  //     // Gestisci la risposta o aggiorna il tuo carrello locale se necessario
  //   });
  // }

  apriCarrello() {
    this.carrelloAperto = !this.carrelloAperto;
  }

  chiudiCarrello() {
    this.carrelloAperto = false;
  }

  calcolaTotaleCarrello() {
    let totale = 0;

    // Itera attraverso i prodotti nel carrello e somma i loro prezzi
    for (const prodotto of this.carrello) {
      totale += prodotto.prezzo;
    }

    return totale;
  }


  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }

}
