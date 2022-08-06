import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReservationService } from '../service/reservation.service';

@Component({
  selector: 'app-define-percentage',
  templateUrl: './define-percentage.component.html',
  styleUrls: ['./define-percentage.component.css']
})
export class DefinePercentageComponent implements OnInit {

  public loginForm :FormGroup;
  errorMessage = '';
  isLoginFailed=false;

  constructor(private fb: FormBuilder, private service: ReservationService) { }

  ngOnInit(): void {
    this.loginForm=this.fb.group({
      percentage: [''],//,Validators.email
    })
  }

  definePercentage(){
    let perc = this.loginForm.get('percentage').value;
    this.service.definePercentage(perc).subscribe(
      res => {
        
      }
    )
  }

}
