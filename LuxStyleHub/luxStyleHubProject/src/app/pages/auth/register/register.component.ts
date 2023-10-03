import { transition, trigger, useAnimation } from '@angular/animations';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { fadeInAnimation, fadeOutAnimation } from 'src/app/animations/animations';
import { Iregister } from 'src/app/interfaces/iregister';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
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

export class RegisterComponent implements OnInit{

  @ViewChild('f')
  form!: NgForm
  error: undefined | string;
  constructor(private authSvc:AuthService, private router: Router){

  }
    data:Iregister = {
      name: '',
      username: '',
      email: '',
      password: ''
    }


    register(){
      this.authSvc.signUp(this.data).subscribe(accessData => {
        alert(accessData.username)
      })
    }

    showPage = false;

    ngOnInit(){
      this.showPage = true;
    }

    leavePage() {
      this.showPage = false; // Attiva l'animazione di uscita
    }

}
