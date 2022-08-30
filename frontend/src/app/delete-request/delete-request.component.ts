import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-delete-request',
  templateUrl: './delete-request.component.html',
  styleUrls: ['./delete-request.component.css']
})
export class DeleteRequestComponent implements OnInit {

  delForm: FormGroup;
  constructor(private fb: FormBuilder, private service: UserService) { }

  ngOnInit(): void {
    this.delForm = this.fb.group({
      reason: []
    })
  }

  makeDelRequest(){
    let reason = this.delForm.get('reason').value;
    this.service.makeDelRequest(reason).subscribe(
      res=> {
        
      }
    )
  }

}
