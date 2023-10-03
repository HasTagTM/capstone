export interface IRecensioni {
  prodotto: {
    id: number;
    // altre proprietà del prodotto
  };
  testo: string;
  valutazione: number;
  dataPubblicazione: Date // Considera di utilizzare una stringa per la data
  user: {
    id: number;
  };
}

