import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Iprodotto } from '../interfaces/iprodotto';
import { AuthService } from './auth.service';
import { IUser } from '../interfaces/iuser';
import { Page } from '../interfaces/ipage';
import { Categories } from '../enumerated/categories';
import { Observable, catchError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProdottoService {
  apiUrl:string = environment.apiProdotti;
  prodotti: Iprodotto[] = [];
  constructor(private http:HttpClient){
    this.getToken();
    console.log(this.getToken());

  }

  getToken(): string{
    const user = localStorage.getItem('user');
    if(user){
      const userData = JSON.parse(user);
      return userData.accessToken;

    }else{
      return '';
    }
  }

  getAll(){
    return this.http.get<Iprodotto[]>(this.apiUrl);
  }

  getCategories(categoria: String) {
    const url = `${this.apiUrl}/categories?categoria=${categoria}`;
    return this.http.get<Iprodotto[]>(url).pipe(
      catchError((error: any) => {
        console.error('Errore nella richiesta HTTP:', error);
        throw error;
      })
    );
  }

  getPage(nPagina:number, size:number){
    return this.http.get<Page>(environment.apiUrl+"?page="+nPagina+"&size="+size);
  }

  getById(id:number){
    return this.http.get<Iprodotto>(`${this.apiUrl}/${id}`);
  }

  post(data:Iprodotto){
    return this.http.post<Iprodotto>(`${this.apiUrl}`,data);
  }

  put(data:Iprodotto){
    return this.http.put<Iprodotto>(`${this.apiUrl}/${data.id}`,data);
  }

  delete(id:number){
    return this.http.delete<Iprodotto>(`${this.apiUrl}/${id}`);
  }

  deletePerCategories(categoria: Categories) {
    return this.http.delete<Iprodotto>(`${this.apiUrl}/${categoria}`);
  }

  aggiungiAiPreferiti(prodottoId: number): Observable<Iprodotto> {
    const url = `${this.apiUrl}/preferiti/${prodottoId}`;
    return this.http.post<Iprodotto>(url, {});
  }

  rimuoviDaPreferiti(prodottoId: number): Observable<any> {
    const url = `${this.apiUrl}/preferiti/${prodottoId}`;
    return this.http.delete<Iprodotto>(url);
  }

  cercaPerCarattere(carattere: string): Observable<Iprodotto[]> {
    const url = `${this.apiUrl}?carattere=${carattere}`;
    return this.http.get<Iprodotto[]>(url);
  }

}
