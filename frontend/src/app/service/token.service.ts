import { Injectable } from '@angular/core';
import jwtDecode from 'jwt-decode';
import { of } from 'rxjs';

const TOKEN_KEY = 'auth-token';
const ROLE = 'user-role';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  isLogin = false;

  roleAs: string;
  constructor() { }

  login(token) {
    this.isLogin = true;
    this.saveToken(token);
    this.saveRole(token);
    localStorage.setItem('STATE', 'true');
    return of({ success: this.isLogin});
  }

  logout() {
    this.isLogin = false;
    this.roleAs = '';
    localStorage.clear();
    localStorage.setItem('STATE', 'false');
    return of({ success: this.isLogin});
  }

  isLoggedIn() {
    const loggedIn = localStorage.getItem('STATE');
    if (loggedIn == 'true')
      this.isLogin = true;
    else
      this.isLogin = false;
    return this.isLogin;
  }


  public saveToken(token: string): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY);
  }

  public saveRole(token): void {
    localStorage.removeItem(ROLE);
    var decoded_token=jwtDecode(token);
    this.roleAs =decoded_token['role'];
    localStorage.setItem(ROLE, this.roleAs );
  }

  public getRole(): any {
    return localStorage.getItem(ROLE);
  }
}
