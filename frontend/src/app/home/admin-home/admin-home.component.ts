import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  constructor(private tokenStorageService: TokenService,private router: Router) { }

  ngOnInit(): void {
  }

  logOut(){
    this.tokenStorageService.logout().subscribe(res=>
      {
        this.router.navigate(['login']);
      }
    );
  }
}
