import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CottageDTO } from '../model/cottageDTO';
import { LessonDTO } from '../model/lessonDTO';
import { ShipDTO } from '../model/shipDTO';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  private readonly _APIUrl="http://localhost:8082/service";
  constructor(private http: HttpClient, private router: Router) { }


  getShips(){
    return this.http.get<ShipDTO[]>(this._APIUrl+ "/getShips");
  }

  getLessons(){
    return this.http.get<LessonDTO[]>(this._APIUrl+ "/getLessons");
  }

  getCottages(){
    return this.http.get<CottageDTO[]>(this._APIUrl+ "/getCottages");
  }
}
