import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-client-home',
  templateUrl: './client-home.component.html',
  styleUrls: ['./client-home.component.css']
})
export class ClientHomeComponent implements OnInit {

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
