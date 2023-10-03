import { Iprodotto } from "./iprodotto";

export interface IOrdine {
  id: number;
  utenteId: number; // L'ID dell'utente che effettua l'ordine
  dataOrdine?: string;
  prodotti: Iprodotto[]; // Una lista di prodotti nell'ordine
  totale: number; // Il totale dell'ordine
}
