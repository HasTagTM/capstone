
import { Categories } from "../enumerated/categories";
import { Genere } from "../enumerated/genere";
import { IRecensioni } from "./irecensioni";

export interface Iprodotto {

  id: number;
  nome: string;
  descrizione: string;
  prezzo: number;
  immagineUrl: string;
  quantita: number;
  genere: Genere;
  categoria: Categories;
  preferito: boolean;
  recensione: string

}
