import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { DeleteRequestDTO } from '../model/delRequestDTO';
import { UserDTO } from '../model/userDTO';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-delete-requests',
  templateUrl: './delete-requests.component.html',
  styleUrls: ['./delete-requests.component.css']
})
export class DeleteRequestsComponent implements OnInit {

  data: DeleteRequestDTO[] = new Array();
  check: boolean[] = new Array();
  ids: number[] = new Array();
  delFormAcc: FormGroup;
  delFormRej: FormGroup;
  constructor(private service: UserService, private fb: FormBuilder) { }
 

  ngOnInit(): void {
    this.delFormAcc = this.fb.group({
      message: [""]
    })

    this.delFormRej = this.fb.group({
      message: [""]
    })
    this.getRequests();
  }

  getRequests(){
    this.service.getDelRequests().subscribe(
      res => {
        this.data = res;
      }
    )
  }

  accept(i:number, evt){
    let form = document.getElementById("form"+i);
    let button = document.getElementById("button"+i + "acc");
    
    let button2 = document.getElementById("button"+i + "rej");
    form.insertBefore(button, button2);
    console.log(button)
    if(button.style.display === "inline-block"){
      button.style.display = "none";
    }
    else if(button.style.display === "none"){
      button.style.display = "inline-block";
      button2.style.display = "none";
      for(let j = 0; j<this.ids.length;j++){
        let buttoni = document.getElementById("button"+j + "acc");
        
        if(j != i){
          buttoni.style.display = "none"
        }

        
      }
    }
  }

  reject(i:number, evt){
    let form = document.getElementById("form"+i);
    let button = document.getElementById("button"+i + "rej");
    
    let button2 = document.getElementById("button"+i + "acc");
    form.insertBefore(button2, button);
    if(button.style.display === "inline-block"){
      button.style.display = "none";
    }
    else if(button.style.display === "none"){
      button.style.display = "inline-block";
      button2.style.display = "none";
      for(let j = 0; j<this.ids.length;j++){
        let buttoni = document.getElementById("button"+j + "rej");
        
        if(j != i){
          buttoni.style.display = "none"
          

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


  submitRej(id: number){
    let message = this.delFormAcc.get("message").value;
    this.service.deleteRequestReject(id, message).subscribe(
      res => {
        window.location.reload()
      },
      err => {
        window.location.reload()
      }
    )

  }

  submitAcc(id: number){
    let message = this.delFormAcc.get("message").value;
    this.service.deleteRequestAccept(id, message).subscribe(
      res => {
        window.location.reload()
      },
      err => {
        window.location.reload()
      }
    )

  }


}
