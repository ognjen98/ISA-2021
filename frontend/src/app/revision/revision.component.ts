import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RevisionDTO } from '../model/revisionDTO';
import { RevisionService } from '../service/revision.service';

@Component({
  selector: 'app-revision',
  templateUrl: './revision.component.html',
  styleUrls: ['./revision.component.css']
})
export class RevisionComponent implements OnInit {

  id: number;
  revForm: FormGroup;
  constructor(private route: ActivatedRoute, private fb: FormBuilder, private service: RevisionService) { }

  ngOnInit(): void {
    this.revForm = this.fb.group({
      type: [],
      grade: [],
      text: []
    })

    this.route.params
      .subscribe(params => {
       this.id = params['id'];// you should have your id here.
       
      });

  }

  revision(){
    let type = this.revForm.get("type").value;
    let grade = this.revForm.get("grade").value;
    let text = this.revForm.get("text").value;

    this.service.saveRevision(new RevisionDTO(null, grade, text, type, this.id)).subscribe(
      res => {

      }
    )
  }



}
