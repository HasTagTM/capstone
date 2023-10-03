import { transition, trigger, useAnimation } from '@angular/animations';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Iprodotto } from 'src/app/interfaces/iprodotto';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css'],
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
export class DetailComponent {
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
  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }
}
