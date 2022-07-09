import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AdditionalInfo } from '../model/additionalInfo';
import { ReservationDTO } from '../model/reservationDTO';
import { SearchDataDTO } from '../model/searchDataDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { SortDTO } from '../model/sortDTO';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private readonly _APIUrl="http://localhost:8082/service";
  constructor(private http: HttpClient, private router: Router) { }


  search(searchData: SearchDataDTO){
    return this.http.post<ServiceDTO[]>(this._APIUrl + "/search", searchData);
  }

  sort(sortData: SortDTO){
    return this.http.post<ServiceDTO[]>(this._APIUrl + "/sort", sortData);
  }

  getInfos(serviceId: number){
    return this.http.get<AdditionalInfo[]>(this._APIUrl+ "/getInfos/"+ serviceId);
  }

  reserve(reservationDTO: ReservationDTO){
    return this.http.post(this._APIUrl + "/reserve", reservationDTO);
  }
}
