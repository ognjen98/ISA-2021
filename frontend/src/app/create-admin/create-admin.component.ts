import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminDTO } from '../model/adminDTO';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-create-admin',
  templateUrl: './create-admin.component.html',
  styleUrls: ['./create-admin.component.css']
})
export class CreateAdminComponent implements OnInit {

  adminForm: FormGroup;
  constructor(private fb: FormBuilder, private service:AdminService) { }

  ngOnInit(): void {
    this.adminForm = this.fb.group({
      name: ["", Validators.required],
      surname: ["", Validators.required],
      email: ["",[Validators.email, Validators.required]]
    })
  }

  createAdmin(){
    let name = this.adminForm.get("name").value;
    let surname = this.adminForm.get("surname").value;
    let email = this.adminForm.get("email").value;
    this.service.createAdmin(new AdminDTO(email, name, surname, null)).subscribe(
      res => {

      }
    )
  }

}
