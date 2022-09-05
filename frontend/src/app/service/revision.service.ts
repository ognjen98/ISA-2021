import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Revision } from '../model/revision';
import { RevisionDTO } from '../model/revisionDTO';

@Injectable({
  providedIn: 'root'
})
export class RevisionService {

  private readonly _APIUrl="http://localhost:8082/revision"
  
  //private readonly _APIUrl="http://localhost:8080/api/user/user"
  constructor(private _http: HttpClient) { }

  saveRevision(dto: RevisionDTO){
    return this._http.post(this._APIUrl + "/saveRevision", dto)
  }

  accept(id: number){
    return this._http.get(this._APIUrl + "/approve/"+ id); 
  }

  reject(id: number){
    return this._http.get(this._APIUrl + "/reject/"+ id); 
  }

  getRev(){
    return this._http.get<Revision[]>(this._APIUrl + "/getRevisions");
  }

  getSerRev(id:number){
    return this._http.get<RevisionDTO[]>(this._APIUrl + "/getSerRevisions/"+id)
  }

  getSelRev(id:number){
    return this._http.get<RevisionDTO[]>(this._APIUrl + "/getSelRevisions/"+id)
  }
}
