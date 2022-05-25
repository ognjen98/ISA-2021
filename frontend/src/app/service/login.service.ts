import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenAuthenticationRequest } from '../model/authenticationRequest';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly _APIUrl="http://localhost:8082/login"
  constructor(private _http: HttpClient) { }
  
  Login(credentials:TokenAuthenticationRequest):Observable<any> {
    return this._http.post(this._APIUrl + '/login', credentials);
  }
}
