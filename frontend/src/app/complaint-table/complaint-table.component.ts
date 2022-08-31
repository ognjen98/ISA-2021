import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Complaint } from '../model/complaint';
import { ComplaintService } from '../service/complaint.service';

@Component({
  selector: 'app-complaint-table',
  templateUrl: './complaint-table.component.html',
  styleUrls: ['./complaint-table.component.css']
})
export class ComplaintTableComponent implements OnInit {

  data: Complaint[] = new Array();
  resForm: FormGroup;
  constructor(private service: ComplaintService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.resForm = this.fb.group({
      message: []
    })

    this.getCom();
  }

  getCom(){
    this.service.getComps().subscribe(
      res => {
        this.data = res;
      }
    )
  }

  submit(id:number){
    let message = this.resForm.get('message').value
    this.service.respond(id, message).subscribe(
      res=> {

      }
    )
  }

}
