import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CPPDTO } from '../model/cppDTO';
import { UpdateInfoDTO } from '../model/updateInfo';
import { ReservationService } from '../service/reservation.service';
import { TokenService } from '../service/token.service';
import { UpdateInfoService } from '../service/update-info.service';

@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrls: ['./update-info.component.css']
})
export class UpdateInfoComponent implements OnInit {

  
  infoForm: FormGroup;
  dto: CPPDTO;
  changeInfo: UpdateInfoDTO=new UpdateInfoDTO("","","","","","","","");
  role: any;
  name:string;
  constructor(private fb: FormBuilder, private service: UpdateInfoService,private  resService: ReservationService, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.getInfo();
  // console.log(this.changeInfo);
    this.infoForm=this.fb.group({
      name: ["", [Validators.required]],
      surname: ["", [Validators.required]],
      mobile: ["", [Validators.pattern("[0-9]{9,10}")]],
      streetName: ["", [Validators.required]],
      number: ["", [Validators.required]],
      city: ["", [Validators.required]],
      state: ["", [Validators.required]],
    })
    
    this.getMisc();
    this.getRole();
  }

  getInfo(){
    this.service.getUserInfo().subscribe(
      res => 
      {
        this.changeInfo = res;
        console.log(this.changeInfo);
        this.infoForm.get('name').setValue(this.changeInfo.name);
        this.infoForm.get('surname').setValue(this.changeInfo.surname);
        this.infoForm.get('mobile').setValue(this.changeInfo.mobile);
        this.infoForm.get('streetName').setValue(this.changeInfo.streetName);
        this.infoForm.get('number').setValue(this.changeInfo.number);
        this.infoForm.get('city').setValue(this.changeInfo.city);
        this.infoForm.get('state').setValue(this.changeInfo.state);

      },
      err => {
        console.log(err);
        }
    );
  }

  submit(){
    this.changeInfo.name = this.infoForm.value.name;
    this.changeInfo.surname = this.infoForm.value.surname;
    this.changeInfo.streetName = this.infoForm.value.streetName;
    this.changeInfo.number = this.infoForm.value.number;
    this.changeInfo.city = this.infoForm.value.city;
    this.changeInfo.state = this.infoForm.value.state;
    this.changeInfo.mobile = this.infoForm.value.mobile;

    
    
    this.service.changeInfo(this.changeInfo).subscribe(
      res=>{
          
      },
      err =>{

      }
    );
  }

  getMisc(){
    this.resService.getMisc().subscribe(
      res => {
        this.dto = res;
      }
    )
  }

  getRole(){
   this.role = this.tokenService.getRole()
  }


}
