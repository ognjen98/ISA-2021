import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
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
}
