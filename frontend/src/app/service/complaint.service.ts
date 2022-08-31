import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Complaint } from '../model/complaint';
import { ComplaintDTO } from '../model/complaintDTO';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  private readonly _APIUrl="http://localhost:8082/complaint"
  
  //private readonly _APIUrl="http://localhost:8080/api/user/user"
  constructor(private _http: HttpClient) { }

  complaint(dto: ComplaintDTO){
    return this._http.post(this._APIUrl + "/makeComplaint", dto)
  }

  getComps(){
    return this._http.get<Complaint[]>(this._APIUrl + "/getComplaints")
  }

  respond(id: number, response: string){
    return this._http.get(this._APIUrl + "/response/" + id + "?response=")
  }
}
