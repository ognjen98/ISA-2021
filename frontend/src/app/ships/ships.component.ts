import { Component, OnInit } from '@angular/core';
import { ShipDTO } from '../model/shipDTO';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-ships',
  templateUrl: './ships.component.html',
  styleUrls: ['./ships.component.css']
})
export class ShipsComponent implements OnInit {

  returnData: ShipDTO[];
  constructor(private service: ServiceService) { }

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

}
