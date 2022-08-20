import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AdditionalInfo } from '../model/additionalInfo';
import { DayMonthValueDTO } from '../model/dayMontValueDTO';
import { GetReservationDTO } from '../model/getReservationDTO';
import { ReportDTO } from '../model/reportDTO';
import { ReservationDTO } from '../model/reservationDTO';
import { SearchDataDTO } from '../model/searchDataDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { SortDTO } from '../model/sortDTO';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private readonly _APIUrl="http://localhost:8082/reservation";
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

  getResForClient(){
    return this.http.get<GetReservationDTO[]>(this._APIUrl+ "/getResForClient");
  }

  cancel(resId:number){
    return this.http.get(this._APIUrl+ "/cancel/"+ resId);
  }

  definePercentage(perc:number){
    // let queryParams = new HttpParams().append("percentage",perc);
    return this.http.get(this._APIUrl+ "/definePercentage?percentage="+ perc);
  }

  getReport(dto: ReportDTO){
    return this.http.post<DayMonthValueDTO[]>(this._APIUrl + "/getReport", dto);
  }
}
