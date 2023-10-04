import { transition, trigger, useAnimation } from '@angular/animations';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Categories } from 'src/app/enumerated/categories';
import { Genere } from 'src/app/enumerated/genere';
import { Page } from 'src/app/interfaces/ipage';
import { Iprodotto } from 'src/app/interfaces/iprodotto';
import { IUser } from 'src/app/interfaces/iuser';
import { AuthService } from 'src/app/services/auth.service';
import { OrdineService } from 'src/app/services/ordine.service';
import { PagamentoService } from 'src/app/services/pagamento.service';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
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

export class DashboardComponent implements OnInit{
  cuoreRosso = false;

  carrelloAperto = false;
  showPage = false;
  risultati: Iprodotto[] = [];
  carattereDiRicerca: string = '';
  carrello: Iprodotto[] = [];
  prodotti: Iprodotto[] = [];
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
  prodottiJeans: Iprodotto[] = [];
  prodottiT_shirt: Iprodotto[] = [];
  prodottiSweatshirt: Iprodotto[] = [];
  prodottiShoes: Iprodotto[] = [];
  loading: boolean = true;
  protected user: IUser | null = null;
  protected isAdmin: boolean = false;
  isAggiuntoAiPreferiti: boolean = false;


  newProduct!:Iprodotto
  animationComplete = false;
  handleImageError(prodotto: Iprodotto) {
    console.error(`Errore nel caricamento dell'immagine per il prodotto: ${prodotto.nome}`);
    // Puoi anche effettuare altre azioni come mostrare un'immagine di fallback.
  }
  constructor(private prodSvc:ProdottoService, private authSvc: AuthService, private carrelloSvc: OrdineService, pagamentoService: PagamentoService){
    admin: authSvc.isLoggedin$.subscribe(user => {
      this.user?.roles.forEach(r => {
        if (r.roleName === "ROLE_ADMIN") this.isAdmin = true
      });
    });
  }

  ngOnInit(){
    this.showPage = true;
    this.getAllProducts();
    console.log('caricamento completato');
  }

  getAllProducts(){
    this.prodSvc.getAll().subscribe((prodotti)=>{
      this.prodotti = prodotti;
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

  create(){
    this.prodSvc.post(this.newProduct)
    .subscribe(data=>{
      this.prodotti.push(data);
    });
  }

  delete(id: number) {
    this.prodSvc.delete(id).subscribe(
      () => {
        const index = this.prodotti.findIndex(p => p.id === id);
        this.prodotti.splice(index, 1);
      },
      (error: HttpErrorResponse) => {
        console.error('Errore nella richiesta di eliminazione:', error);
      }
    );
  }

  deleteDog(id: number) {
    this.prodSvc.deletePerCategories(id).subscribe((data) => {
      let index = this.prodottiJeans.findIndex((p) => p.id == id);
      this.prodottiJeans.splice(index, 1);
    });
  }

  deleteCat(id: number) {
    this.prodSvc.deletePerCategories(id).subscribe((data) => {
      let index = this.prodottiShoes.findIndex((p) => p.id == id);
      this.prodottiShoes.splice(index, 1);
    });
  }

  deleteBird(id: number) {
    this.prodSvc.deletePerCategories(id).subscribe((data) => {
      let index = this.prodottiSweatshirt.findIndex((p) => p.id == id);
      this.prodottiSweatshirt.splice(index, 1);
    });
  }

  deleteHamster(id: number) {
    this.prodSvc.deletePerCategories(id).subscribe((data) => {
      let index = this.prodottiT_shirt.findIndex((p) => p.id == id);
      this.prodottiT_shirt.splice(index, 1);
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

  apriPagamento() {
    this.apriPagamento();
  }

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

  cercaProdotti() {
    if (this.carattereDiRicerca) {
      // Converte il carattere di ricerca in minuscolo (per rendere la ricerca non sensibile al caso)
      const carattereMinuscolo = this.carattereDiRicerca.toLowerCase();

      // Filtra i prodotti in base al carattere inserito
      this.prodotti = this.prodotti.filter(prodotto =>
        prodotto.nome.toLowerCase().startsWith(carattereMinuscolo)
      );
    } else {
      // Se il campo di ricerca è vuoto, mostra tutti i prodotti
      this.getAllProducts();
    }
  }



  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }
}
