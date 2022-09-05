import { Component, OnInit } from '@angular/core';
import { Revision } from '../model/revision';
import { RevisionService } from '../service/revision.service';

@Component({
  selector: 'app-revision-table',
  templateUrl: './revision-table.component.html',
  styleUrls: ['./revision-table.component.css']
})
export class RevisionTableComponent implements OnInit {

  data: Revision[] = new Array();
  constructor(private service: RevisionService) { }

  ngOnInit(): void {

    this.getRev();
  }

  getRev(){
    this.service.getRev().subscribe(
      res => {
        this.data = res;
      }
    )
  }

  accept(id: number){
    this.service.accept(id).subscribe(
      res=> {
        window.location.reload()
      },
      err => {
        window.location.reload()
      }
    )
  }

  reject(id: number){
    this.service.reject(id).subscribe(
      res=> {
        window.location.reload()
      },
      err => {
        window.location.reload()
      }
    )
  }

}
