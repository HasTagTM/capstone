import { Component } from '@angular/core';
import { IPagamento } from 'src/app/interfaces/ipagamento';
import { DashboardComponent } from 'src/app/pages/dashboard/dashboard/dashboard.component';

@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.css']
})
export class PagamentoComponent {

  constructor(private dashboardComponent: DashboardComponent) {}

  get totaleCarrello(): number {
    return this.dashboardComponent.calcolaTotaleCarrello();
  }

  pagamento: IPagamento = {
    titolareCarta: '',
    numeroCarta: '',
    dataScadenza: '',
    codiceSicurezza: ''
  };

  pagamentoSimulatoConSuccesso = false;
  erroreNelPagamento = false;

  effettuaPagamento() {
    // Simula il pagamento (puoi personalizzare questa logica)
    if (this.pagamento.numeroCarta === '1234') {
      this.pagamentoSimulatoConSuccesso = true;
      this.erroreNelPagamento = false;
    } else {
      this.pagamentoSimulatoConSuccesso = false;
      this.erroreNelPagamento = true;
    }
  }
}


