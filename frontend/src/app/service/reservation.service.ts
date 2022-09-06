import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AdditionalInfo } from '../model/additionalInfo';
import { CPPDTO } from '../model/cppDTO';
import { DayMonthValueDTO } from '../model/dayMontValueDTO';
import { FilterDTO } from '../model/filterDTO';
import { GetReservationDTO } from '../model/getReservationDTO';
import { InhrCottageDTO } from '../model/inhrCottageDTO';
import { InhrShipDTO } from '../model/inhrShipDTO';
import { ReportDTO } from '../model/reportDTO';
import { ReservationDTO } from '../model/reservationDTO';
import { SearchDataDTO } from '../model/searchDataDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { SortDTO } from '../model/sortDTO';
import { SortDTOCottage } from '../model/sortDTOCottage';
import { SortDTOShip } from '../model/sortDTOShip';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private readonly _APIUrl="http://localhost:8082/reservation";
  constructor(private http: HttpClient, private router: Router) { }


  searchShips(searchData: SearchDataDTO){
    return this.http.post<InhrShipDTO[]>(this._APIUrl + "/searchShips", searchData);
  }

  searchCottages(searchData: SearchDataDTO){
    return this.http.post<InhrCottageDTO[]>(this._APIUrl + "/searchCottages", searchData);
  }

  searchLessons(searchData: SearchDataDTO){
    return this.http.post<ServiceDTO[]>(this._APIUrl + "/searchLessons", searchData);
  }

  sort(sortData: SortDTO){
    return this.http.post<ServiceDTO[]>(this._APIUrl + "/sort", sortData);
  }

  sortShips(sortData: SortDTOShip){
    return this.http.post<InhrShipDTO[]>(this._APIUrl + "/sortShips", sortData);
  }


  sortCottages(sortData: SortDTOCottage){
    return this.http.post<InhrCottageDTO[]>(this._APIUrl + "/sortCottages", sortData);
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

  getShipReservations(){
    return this.http.get<GetReservationDTO[]>(this._APIUrl + "/getShipReservations")
  }

  getLessonsReservations(){
    return this.http.get<GetReservationDTO[]>(this._APIUrl + "/getLessonsReservations")
  }

  getCottageReservations(){
    return this.http.get<GetReservationDTO[]>(this._APIUrl + "/getCottageReservations")
  }

  filter(dto: FilterDTO){
    return this.http.post<InhrShipDTO[]>(this._APIUrl + "/filter", dto);
  }

  getMisc(){
    return this.http.get<CPPDTO>(this._APIUrl + "/getMisc");
  }
}
