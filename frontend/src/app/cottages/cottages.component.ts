import { Component, OnInit } from '@angular/core';
import { CottageDTO } from '../model/cottageDTO';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-cottages',
  templateUrl: './cottages.component.html',
  styleUrls: ['./cottages.component.css']
})
export class CottagesComponent implements OnInit {

  returnData: CottageDTO[];
  constructor(private service: ServiceService) { }

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

}
