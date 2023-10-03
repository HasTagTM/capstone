import { transition, trigger, useAnimation } from '@angular/animations';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
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
export class HeaderComponent {
  isLogged: boolean = false;

  constructor(public authSvc: AuthService, private router: Router) {}
  showPage = false;

  ngOnInit() {
    this.showPage = true;
    this.isLoggedIn();
  }

  logout() {
    this.authSvc.logout();
  }

  isLoggedIn() {
    const userJson = localStorage.getItem('user');
    if (userJson) {
      this.isLogged = true;
    }
  }


  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }


}
