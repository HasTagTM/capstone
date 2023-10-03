import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IOrdine } from '../interfaces/iordine';
import { Iprodotto } from '../interfaces/iprodotto';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';


@Injectable({
  providedIn: 'root'
})
export class OrdineService {
  apiUrl:string = environment.apiCarrello;

  private carrello: IOrdine = {
    id: 0,
    utenteId: 0,
    prodotti: [],
    totale: 0,
  };

  private carrelloSubject = new BehaviorSubject<IOrdine>(this.carrello);
  constructor(private http: HttpClient) {}

  aggiungiAlCarrello(prodotto: Iprodotto): void {
    // Aggiungi il prodotto al carrello finto
    this.carrello.prodotti.push(prodotto);
    // Aggiorna il totale del carrello finto
    this.carrello.totale += prodotto.prezzo;
  }

  rimuoviDalCarrello(prodotto: Iprodotto): void {
    // Trova l'indice del prodotto nel carrello finto
    const index = this.carrello.prodotti.findIndex(p => p.id === prodotto.id);
    if (index !== -1) {
      // Rimuovi il prodotto dal carrello finto
      this.carrello.prodotti.splice(index, 1);
      // Aggiorna il totale del carrello finto
      this.carrello.totale -= prodotto.prezzo;
    }
  }
  getCarrello(): IOrdine {
    return this.carrello;
  }


  // getCarrello(): Observable<IOrdine> {
  //   return new Observable<IOrdine>((observer) => {
  //     observer.next(this.carrello);
  //   });
  // }

  // aggiungiAlCarrello(prodotto: Iprodotto): Observable<IOrdine> {
  //   // Creare un oggetto ordine con il prodotto, la data corrente e altri campi
  //   const nuovoOrdine: IOrdine = {
  //     id: 0, // L'ID verrà generato dal server
  //     utenteId: 0, // Sostituisci con l'ID dell'utente appropriato
  //     prodotti: [prodotto], // Metti il prodotto nel campo "prodotti"
  //     totale: prodotto.prezzo, // Imposta il prezzo totale del prodotto
  //     dataOrdine: new Date().toISOString(), // Converte la data corrente in una stringa ISO
  //   };

  //   // Invia la richiesta al backend per aggiungere il prodotto al carrello
  //   return this.http.post<IOrdine>(`${this.apiUrl}/carrello`, nuovoOrdine);
  // }


  // rimuoviDalCarrello(prodotto: Iprodotto): Observable<IOrdine> {
  //   // Invia la richiesta al backend per rimuovere il prodotto dal carrello
  //   return this.http.delete<IOrdine>(`${this.apiUrl}/carrello/${prodotto.id}`);
  // }
}

