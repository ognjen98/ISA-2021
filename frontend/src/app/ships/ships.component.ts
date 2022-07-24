import { Component, OnInit } from '@angular/core';
import { ServiceDTO } from '../model/serviceDTO';
import { ShipDTO } from '../model/shipDTO';
import { SortDTO } from '../model/sortDTO';
import { ReservationService } from '../service/reservation.service';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-ships',
  templateUrl: './ships.component.html',
  styleUrls: ['./ships.component.css']
})
export class ShipsComponent implements OnInit {

  returnData: ServiceDTO[];
  sortDTO: SortDTO;
  constructor(private service: ServiceService, private service2: ReservationService) { }

  ngOnInit(): void {
    this.getShips();
  }


  getShips(){
    this.service.getShips().subscribe(
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
