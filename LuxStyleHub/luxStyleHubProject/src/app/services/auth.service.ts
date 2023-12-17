import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, catchError, map, tap, throwError } from 'rxjs';
import { Ilogin } from '../interfaces/ilogin';
import { Iregister } from '../interfaces/iregister';
import { environment } from 'src/environments/environment.development';
import { getNumberOfCurrencyDigits } from '@angular/common';
import { IUser } from '../interfaces/iuser';


@Injectable({
  providedIn: 'root'
})
export class AuthService {


  jwtHelper: JwtHelperService = new JwtHelperService()
  apiUrl = environment.apiUrl
  private authSubject = new BehaviorSubject<null | IUser>(null)
  user$ = this.authSubject.asObservable()
  // isLoggedin$ = this.user$.pipe(map(dato => Boolean(dato)));
  isLoggedin$ = this.user$.pipe(
    map(dato => dato !== null && !this.jwtHelper.isTokenExpired(dato.accessToken))
  );
  isLogged: boolean = false;
  isAdminOrModerator(): boolean {
    const roles = this.getRolesFromToken();
    return roles.includes('ROLE_ADMIN') || roles.includes('ROLE_MODERATOR');
  }

  getRolesFromToken(): string[] {
    const userJson = localStorage.getItem('user');
    if (userJson) {
      const user = JSON.parse(userJson);
      if (user && user.accessToken) {
        const decodedToken = this.jwtHelper.decodeToken(user.accessToken);
        if (decodedToken && decodedToken.roles) {
          return decodedToken.roles; // Supponendo che i ruoli siano presenti nell'array "roles" del token
        }
      }
    }
    return [];
  }

  authLogoutTimer: any;

  constructor(private http: HttpClient, private router:Router) {
    this.restoreUser()
  }

  signUp(data:Iregister){
    return this.http.post<Ilogin>(this.apiUrl + 'api/auth/product/register', data)
    .pipe(catchError(this.errors))
  }

  login(data:Ilogin){
    return this.http.post<IUser>(this.apiUrl + 'api/auth/login', data).pipe(tap(data => {
      this.authSubject.next(data);
      localStorage.setItem('user', JSON.stringify(data));
      const expDate = this.jwtHelper.getTokenExpirationDate(data.accessToken)as Date;
      this.autoLogout(expDate)
    }),
    catchError(this.errors))
  }

  restoreUser(){
    const userJsn = localStorage.getItem('user')
    if(!userJsn){
      return
    }
    const user:IUser = JSON.parse(userJsn)
    if(this.jwtHelper.isTokenExpired(user.accessToken)){
      return
    }
    this.authSubject.next(user)
  }

  logout(){
    this.authSubject.next(null);
    localStorage.removeItem('user')
    this.router.navigate(['/login']);
    if(this.authLogoutTimer){
      clearTimeout(this.authLogoutTimer)
    }
  }

  autoLogout(expDate:Date){
    const expMs = expDate.getTime() - new Date().getTime();
    this.authLogoutTimer = setTimeout(() => {

    }, expMs)
  }

  errors(err: any) {
    switch (err.error) {
        case "Email and Password are required":
            return throwError('Email e password obbligatorie');
            break;
        case "Email already exists":
            return throwError('Utente esistente');
            break;
        case 'Email format is invalid':
            return throwError('Email scritta male');
            break;
        case 'Cannot find user':
            return throwError('utente inesistente');
            break;
            default:
        return throwError('Errore');
            break;
    }
  }
}
