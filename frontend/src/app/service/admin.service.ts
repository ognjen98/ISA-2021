import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AdminDTO } from '../model/adminDTO';
import { Category } from '../model/category';
import { Points } from '../model/points';

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

  createAdmin(adminDto: AdminDTO){
    return this.http.post(this._APIUrl + "/createAdmin", adminDto);
  }

  createCategory(dto: Category){
    return this.http.post(this._APIUrl + "/createCategory", dto)
  }

  setPoints(dto: Points){
    return this.http.post(this._APIUrl + "/setPoints", dto)
  }
}
