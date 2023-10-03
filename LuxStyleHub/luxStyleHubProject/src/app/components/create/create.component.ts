import { transition, trigger, useAnimation } from '@angular/animations';
import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Categories } from 'src/app/enumerated/categories';
import { Genere } from 'src/app/enumerated/genere';
import { Iprodotto } from 'src/app/interfaces/iprodotto';
import { ProdottoService } from 'src/app/services/prodotto.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css'],
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
export class CreateComponent {

  Categories = Categories;
  Genere = Genere;
  @ViewChild('f') form!: NgForm;

  prodotto: Iprodotto = {
    nome: '',
    descrizione: '',
    prezzo: 0.0,
    immagineUrl: '',
    quantita: 1,
    genere: Genere.UNISEX,
    categoria: Categories.SCARPE,
    preferito: true,
    recensione: '',
    id: 0
  };
  constructor(
    private prodSvc: ProdottoService,
    private router: Router,
    private route: ActivatedRoute
  ) { }
  showPage = false;

  ngOnInit() {
    this.showPage = true;

  }

  edit() {
    this.prodSvc.post(this.prodotto).subscribe((pizze) => {
      // this.router.navigate(['/create'])
      this.form.reset();
    });
  }

  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }
}

