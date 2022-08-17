import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private readonly _APIUrl="http://localhost:8082/systemAdmin"

  constructor(private http: HttpClient) { }

  checkLogin(){
    return this.http.get<boolean>(this._APIUrl + "/checkFirstTimeLogin");
  }

  changePass(pass:string){
    return this.http.get(this._APIUrl + "/changePass?pass="+pass);
  }
}
