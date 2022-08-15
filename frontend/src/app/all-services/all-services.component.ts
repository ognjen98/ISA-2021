import { Component, OnInit } from '@angular/core';
import { ServiceDTO } from '../model/serviceDTO';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-all-services',
  templateUrl: './all-services.component.html',
  styleUrls: ['./all-services.component.css']
})
export class AllServicesComponent implements OnInit {

  data: ServiceDTO[] = new Array();
  constructor(private service: ServiceService) { }

  ngOnInit(): void {
    this.getServices();
  }


  getServices(){
    this.service.getServices().subscribe(
      res => {
        this.data = res;

      }
    )
  }

  delete(id: number){
    this.service.delete(id).subscribe(
      res => {
          this.getServices()
      }
    )
  }
}
