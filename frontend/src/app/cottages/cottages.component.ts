import { Component, OnInit } from '@angular/core';
import { CottageDTO } from '../model/cottageDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { SortDTO } from '../model/sortDTO';
import { ReservationService } from '../service/reservation.service';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-cottages',
  templateUrl: './cottages.component.html',
  styleUrls: ['./cottages.component.css']
})
export class CottagesComponent implements OnInit {

  returnData: ServiceDTO[];
  sortDTO: SortDTO;
  constructor(private service: ServiceService, private service2: ReservationService) { }

  ngOnInit(): void {
    this.getCottages();
  }


  getCottages(){
    this.service.getCottages().subscribe(
      res => {
        this.returnData = res;
      }
    )
  }

  sort(event){
    let sortParam = event.value;
    console.log(event.value)
    this.sortDTO = new SortDTO(this.returnData, sortParam)
    this.service2.sort(this.sortDTO).subscribe(
      res =>{
        this.returnData = res;
        console.log(this.returnData)
      }
    )
  }

}
