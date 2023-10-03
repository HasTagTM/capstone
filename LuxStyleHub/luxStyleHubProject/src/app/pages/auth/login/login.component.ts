import { transition, trigger, useAnimation } from '@angular/animations';
import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Ilogin } from 'src/app/interfaces/ilogin';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
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

export class LoginComponent implements OnInit{

  data:Ilogin = {
    username: '',
    password: ''
  }

  constructor(private authSvc: AuthService,
    private router: Router){

  }
  showPage = false;

  ngOnInit(){
    this.showPage = true;
  }

  leavePage() {
    this.showPage = false; // Attiva l'animazione di uscita
  }

  login(){
    this.authSvc.login(this.data).subscribe(accessData => {
      this.router.navigate(['/dashboard'])
      alert("ben venuto" + accessData.username)
    })
  }
}
