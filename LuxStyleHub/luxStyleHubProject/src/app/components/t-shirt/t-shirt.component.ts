import { transition, trigger, useAnimation } from '@angular/animations';
import { Component } from '@angular/core';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Categories } from 'src/app/enumerated/categories';
import { Genere } from 'src/app/enumerated/genere';
import { Iprodotto } from 'src/app/interfaces/iprodotto';
import { IUser } from 'src/app/interfaces/iuser';
import { AuthService } from 'src/app/services/auth.service';
import { OrdineService } from 'src/app/services/ordine.service';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
  selector: 'app-t-shirt',
  templateUrl: './t-shirt.component.html',
  styleUrls: ['./t-shirt.component.css'],
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
export class TShirtComponent {
  protected isAdmin: boolean = false;
  carrelloAperto = false;
  carrello: Iprodotto[] = [];
  t_shirt: Iprodotto = {
    id: 0,
    nome: '',
    prezzo: 0,
    descrizione: '',
    immagineUrl: '',
    quantita: 0,
    genere: Genere.UNISEX,
    categoria: Categories.T_SHIRT,
    recensione: '',
    preferito: false
  };

  user: IUser = {
    accessToken: 'string',
    tokenType: '',
    username: '',
    roles: []
  };

  prodotti: Iprodotto[] = [];
  isAggiuntoAiPreferiti: boolean = false;
  constructor(private prodSvc: ProdottoService,private authSvc: AuthService, ordineSvc:OrdineService) {
    admin: authSvc.isLoggedin$.subscribe(user => {
      this.user?.roles.forEach(r => {
        if (r.roleName === "ROLE_ADMIN") this.isAdmin = true
      });
    });
  }
  showPage = false;

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

  ngOnInit() {
    this.showPage = true;
    this.getShoes();
  }
  getShoes() {
    this.prodSvc.getCategories("T_SHIRT").subscribe((data) => {
      this.prodotti = data;
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
