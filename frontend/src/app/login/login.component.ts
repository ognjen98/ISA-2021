import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenAuthenticationRequest } from '../model/authenticationRequest';
import { LoginService } from '../service/login.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm :FormGroup;
  errorMessage = '';
  isLoginFailed=false;

  constructor(private fb: FormBuilder,private authenticationService:LoginService, private tokenStorageService: TokenService,private router: Router) { }

  ngOnInit(): void {
    this.loginForm=this.fb.group({
      username: ['', [Validators.required]],//,Validators.email
      password: ['', [Validators.required ]]
    })
    this.isLoginFailed=false;
  }

  //,Validators.email
  login(){
    var  credentials= new TokenAuthenticationRequest();
    credentials.username=this.loginForm.controls['username'].value;
    credentials.password= this.loginForm.controls['password'].value;

    this.authenticationService.Login(credentials).subscribe(
    (data:any)=>
      {
        this.tokenStorageService.login(data.accessToken)
            .subscribe(res => {
                    if (res.success) 
                    {
                        this.goToDashBoard();
                      }
              });
      },
      err => {
        this.errorMessage = "";
        this.isLoginFailed=true;
      }  
    )
    
  }

  goToDashBoard(){
    let role = this.tokenStorageService.getRole();
    if (role === 'ROLE_SYSTEM_ADMIN')
      this.router.navigate(['admin']);
    if (role === 'ROLE_CLIENT')
      this.router.navigate(['client']);

  } 

}
