import { transition, trigger, useAnimation } from '@angular/animations';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Genere } from 'src/app/enumerated/genere';
import { Iprodotto } from 'src/app/interfaces/iprodotto';
import { IRecensioni } from 'src/app/interfaces/irecensioni';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css'],
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

export class EditComponent {
  prodotto!: Iprodotto;
  constructor(
    private prodSvc: ProdottoService,
    private router: Router,
    private route: ActivatedRoute
  ) { }
  showPage = false;
  ngOnInit() {
    this.showPage = true;
    this.route.params.subscribe((params: any) => {
      this.prodSvc.getById(params.id).subscribe((pizza) => {
        this.prodotto = pizza; // Qui popoliamo il prodotto con i dati recuperati dal servizio
      });
    });
  }

  edit() {
    this.prodSvc.put(this.prodotto).subscribe((pizze) => {
      this.router.navigate(['dashboard'])
    });
  }

  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }
}
