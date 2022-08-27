import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SubscriptionDTO } from '../model/subDTO';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private readonly _APIUrl="http://localhost:8082/subscription";
  constructor(private http: HttpClient, private router: Router) { }

  createSub(serviceId: number){
    return this.http.get(this._APIUrl + "/addSubscription/" + serviceId);
  }

  subbedServices(){
    return this.http.get<SubscriptionDTO[]>(this._APIUrl + "/getSubbedServices");
  }

  cancelSub(id: number){
    return this.http.get(this._APIUrl + "/cancelSub/" + id);
  }

  addResAction(){
    return this.http.get(this._APIUrl + "/addResAction");
  }
}
