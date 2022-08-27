import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { element } from 'protractor';
import { UserDTO } from '../model/userDTO';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-reg-requests',
  templateUrl: './reg-requests.component.html',
  styleUrls: ['./reg-requests.component.css']
})
export class RegRequestsComponent implements OnInit {

  check: boolean[] = new Array();
  ids: number[] = new Array();
  sellerForm: FormGroup;
  data: UserDTO[] = new Array();
  constructor(private service: AdminService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.sellerForm = this.fb.group({
      message: [""]
    })
    this.getRegRequests();
  }

  accept(id:number){
    this.service.accept(id).subscribe(
      res => {
        this.getRegRequests()
      },
      err => {
        this.getRegRequests()
      }
    )
  }

  reject(i:number){
    let form = document.getElementById("form"+i);
    if(form.style.visibility === "visible"){
      form.style.visibility = "hidden";
    }
    else if(form.style.visibility === "hidden"){
      form.style.visibility = "visible";
      for(let j = 0; j<this.ids.length;j++){
        let formi = document.getElementById("form"+j);
        if(j != i){
          formi.style.visibility = "hidden"
        }
      }
    }
    // if(this.check[i] === true){
    //   this.check[i] = false
    // }
    // else{
    //   this.check[i] = true;
    //   for(let j = 0; j < this.check.length; j++){
    //     if(j != i){
    //       this.check[j] = false;
    //     }
    //   }
    // }

  }


  submit(id: number){
    let message = this.sellerForm.get("message").value;
    this.service.reject(id, message).subscribe(
      res => {
        this.getRegRequests()
      },
      err => {
        this.getRegRequests()
      }
    )

  }

  getRegRequests(){
    this.service.regRequests().subscribe(
      res => {
        this.data = res;

        for(let i = 0; i<this.data.length;i++){
          this.ids.push(i);
        }
      }
    )
  }

}
