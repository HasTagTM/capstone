import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {
  isPaid = false;

  constructor() {}

  pay() {
    this.isPaid = true;
  }

}
