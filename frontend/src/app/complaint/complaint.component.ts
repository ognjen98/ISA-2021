import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ComplaintDTO } from '../model/complaintDTO';
import { ComplaintService } from '../service/complaint.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  comForm: FormGroup;
  id: number;
  constructor(private fb: FormBuilder, private service: ComplaintService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.comForm = this.fb.group({
      type: [],
      reason: []
    })

    this.route.params
      .subscribe(params => {
       this.id = params['id'];// you should have your id here.
       
      });
  }

  submit(){
    let reason = this.comForm.get('reason').value;
    let type = this.comForm.get('type').value;
    console.log(type)
    this.service.complaint(new ComplaintDTO(null, reason, type, this.id)).subscribe(
      res=> {
        
      }
    )
  }

}
