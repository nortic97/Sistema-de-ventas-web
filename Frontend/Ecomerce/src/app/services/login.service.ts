import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private decode:any;

  private url:String = 'http://127.0.0.1:8080/api/login?decode=';

  constructor(
    private http:HttpClient
    ) {
      this.decode = '';
     }

    getLogin(usuario:any, decode:any = null): Observable<any>{

      var PARAM_DECODE = "false";

      if(decode != null){

        PARAM_DECODE = "true";

      }

      var json = JSON.stringify(usuario);

      var headers = new HttpHeaders().set('Content-Type', 'application/json');

      return this.http.post<any>(this.url + PARAM_DECODE, json, {headers: headers});

    }

}
