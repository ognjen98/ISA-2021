import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Points } from '../model/points';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-points',
  templateUrl: './points.component.html',
  styleUrls: ['./points.component.css']
})
export class PointsComponent implements OnInit {

  searchForm: FormGroup;
  constructor(private fb: FormBuilder, private service: AdminService) { }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      client: ["", Validators.required],
      seller: ["", Validators.required]
    })
  }

  setPoints(){
    let client = this.searchForm.get('client').value;
    let seller = this.searchForm.get('seller').value;
    this.service.setPoints(new Points(null, client, seller)).subscribe(
      res => {
        
      }
    )
  }

}
