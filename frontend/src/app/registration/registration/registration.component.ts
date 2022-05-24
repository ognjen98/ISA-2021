import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RegistrationDTO } from 'src/app/model/RegistrationDTO';
import { RegistrationService } from 'src/app/service/registration.service';
import { MustMatch ,PasswordStrengthValidator } from '../password.validator';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public registerForm :FormGroup;
  user:RegistrationDTO;
  maxDate: Date;

  constructor(private toastr: ToastrService, private fb: FormBuilder,private router: Router,private regService:RegistrationService) {
    const currentYear = new Date().getFullYear(); 
    this.maxDate=new Date(currentYear-10,12,31);
  }

  ngOnInit(): void {
    this.registerForm=this.fb.group({
      email: ['', [Validators.email,Validators.required]],
      name:['',[Validators.required]],
      surname:['',[Validators.required]],
      mobile: ['', [Validators.pattern("[0-9]{9,10}"),Validators.required]],
      streetName:['', [Validators.required]],
      number:['',[Validators.required]],
      city:['', [Validators.required]],
      state:[,[Validators.required]],
      password: ['', [Validators.required,Validators.minLength(10),PasswordStrengthValidator()]],
      confirmPassword: ['', Validators.required]
    },
    {
      validator: MustMatch("password", "confirmPassword")
    })
  }

  register(){
    this.user=new RegistrationDTO();
    this.user.password=this.registerForm.controls['password'].value;
    this.user.email=this.registerForm.controls['email'].value;
    this.user.name=this.registerForm.controls['name'].value;
    this.user.surname=this.registerForm.controls['surname'].value;
    this.user.mobile=this.registerForm.controls['mobile'].value;
    this.user.streetName=this.registerForm.controls['streetName'].value;
    this.user.number=this.registerForm.controls['number'].value;
    this.user.city=this.registerForm.controls['city'].value;
    this.user.state=this.registerForm.controls['state'].value;
   
    this.regService.registration(this.user).subscribe(
      res => {
        console.log(res)
        this.toastr.info("A verification email has been sent to your email address");/*"In order to complete the registration, it is necessary to verify your account \n"+
        "A verification email has been sent to your email address  "*/
        //this.router.navigate(['/']);
        },
      err=>{
        console.log(err)
        this.toastr.error("An error has occurred" );
        
      }
    )
   
  }


}


