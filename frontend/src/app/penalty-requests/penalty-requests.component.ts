import { Component, OnInit } from '@angular/core';
import { PenaltyRequest } from '../model/penaltyRequest';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-penalty-requests',
  templateUrl: './penalty-requests.component.html',
  styleUrls: ['./penalty-requests.component.css']
})
export class PenaltyRequestsComponent implements OnInit {

  data: PenaltyRequest[] = new Array();
  constructor(private service:AdminService) { }

  ngOnInit(): void {

    this.getRev();
  }

  getRev(){
    this.service.penalties().subscribe(
      res => {
        this.data = res;
      }
    )
  }

  accept(id: number){
    this.service.penaltyAccept(id).subscribe(
      res=> {
        window.location.reload()
      },
      err => {
        window.location.reload()
      }
    )
  }

  reject(id: number){
    this.service.penaltyReject(id).subscribe(
      res=> {
        window.location.reload()
      },
      err => {
        window.location.reload()
      }
    )
  }

}
