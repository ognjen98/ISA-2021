import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MustMatch, PasswordStrengthValidator } from 'src/app/registration/password.validator';
import { AdminService } from 'src/app/service/admin.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  registerForm: FormGroup;
  checkLogin: boolean = true;
  constructor(private tokenStorageService: TokenService,private router: Router, private adminService: AdminService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.registerForm=this.fb.group({
      
      password: ['', [Validators.required,Validators.minLength(10),PasswordStrengthValidator()]],
      confirmPassword: ['', Validators.required]
    },
    {
      validator: MustMatch("password", "confirmPassword")
    });

    this.checkFirstTimeLogin();
  }

  logOut(){
    this.tokenStorageService.logout().subscribe(res=>
      {
        this.router.navigate(['login']);
      }
    );
  }

  checkFirstTimeLogin(){
    this.adminService.checkLogin().subscribe(
      res => {
        this.checkLogin = res;
        console.log(this.checkLogin);
      }
    )
  }

  changePass(){
    let pass = this.registerForm.get("password").value;
    this.adminService.changePass(pass).subscribe(
      res => {
        
      }
    )
    window. location. reload();
    this.router.navigate(['admin']);
  }
}
