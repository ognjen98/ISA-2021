import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegistrationDTO } from '../model/RegistrationDTO';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private readonly _APIUrl="http://localhost:8082/registration"
  
  //private readonly _APIUrl="http://localhost:8080/api/user/user"
  constructor(private _http: HttpClient) { }

  registration(user:RegistrationDTO):Observable<any>{
    return this._http.post(this._APIUrl+'/register',user);

  }
}
