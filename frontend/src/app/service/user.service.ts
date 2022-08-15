import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserDTO } from '../model/userDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly _APIUrl="http://localhost:8082/user";
  constructor(private http: HttpClient, private router: Router) { }

  getUsers(){
    return this.http.get<UserDTO[]>(this._APIUrl + "/allUsers");
  }

  delete(id: number){
    return this.http.delete(this._APIUrl + "/deleteUser/"+ id);
  }
}
