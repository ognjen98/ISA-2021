import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from '../model/category';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  searchForm: FormGroup;
  constructor(private fb: FormBuilder, private service: AdminService) { }

  ngOnInit(): void {
    this.searchForm=this.fb.group({
      type: ["SHIP", ],
      name: ["", Validators.required],
      discount: ["",Validators.required ],
      points: ["", Validators.required],
      
     
    });
  }


  createCategory(){
    let type = this.searchForm.get('type').value;
    let name = this.searchForm.get('name').value;
    let discount = this.searchForm.get('discount').value;
    let points = this.searchForm.get('points').value;
    this.service.createCategory(new Category(null, name, discount, points, type)).subscribe(
      res => {
        
      }
    )
  }

}
