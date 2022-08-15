import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CottageDTO } from '../model/cottageDTO';
import { DiscountReservationDTO } from '../model/discountReservation';
import { LessonDTO } from '../model/lessonDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { ShipDTO } from '../model/shipDTO';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  private readonly _APIUrl="http://localhost:8082/service";
  constructor(private http: HttpClient, private router: Router) { }


  getShips(){
    return this.http.get<ServiceDTO[]>(this._APIUrl+ "/getShips");
  }

  getLessons(){
    return this.http.get<ServiceDTO[]>(this._APIUrl+ "/getLessons");
  }

  getCottages(){
    return this.http.get<ServiceDTO[]>(this._APIUrl+ "/getCottages");
  }

  getDiscountReservations(serviceId: number){
    return this.http.get<DiscountReservationDTO[]>(this._APIUrl+ "/getDiscRes/"+serviceId)
  }

  getServices(){
    return this.http.get<ServiceDTO[]>(this._APIUrl+ "/allServices")
  }

  delete(id: number){
    return this.http.delete(this._APIUrl + "/deleteService/"+ id);
  }
}
