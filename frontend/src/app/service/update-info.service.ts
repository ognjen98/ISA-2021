import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UpdateInfoDTO } from '../model/updateInfo';

@Injectable({
  providedIn: 'root'
})
export class UpdateInfoService {

  private readonly _APIUrl="http://localhost:8082/user";
  constructor(private http: HttpClient, private router: Router) { }

  getUserInfo(): Observable<UpdateInfoDTO>{
    return this.http.get<UpdateInfoDTO>(this._APIUrl + "/userInfo");
  }

  changeInfo(ci: UpdateInfoDTO){
    return this.http.post(this._APIUrl + "/updateInfo", ci, {responseType:"text"});
  }
}
