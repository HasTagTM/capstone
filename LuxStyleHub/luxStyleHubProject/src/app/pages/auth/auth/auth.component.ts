import { transition, trigger, useAnimation } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
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

export class AuthComponent implements OnInit{

  showPage = false;

  ngOnInit(){
    this.showPage = true;
  }

  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }
}
