import { Component, OnInit } from '@angular/core';
import { SubscriptionDTO } from '../model/subDTO';
import { SubscriptionService } from '../service/subscription.service';

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {


  
  returnData: SubscriptionDTO[] = new Array();
 
  serviceId: number;

  constructor(private service: SubscriptionService) { }

  ngOnInit(): void {

    this.getSubbedServices();
  }


  getSubbedServices(){
    this.service.subbedServices().subscribe(
      res => {
        this.returnData = res;
      }
    )
  }

  cancelSub(id: number){
    this.service.cancelSub(id).subscribe(
      res => {
        this.getSubbedServices()
      }
    )
  }
}
