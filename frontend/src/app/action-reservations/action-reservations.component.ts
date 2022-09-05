import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdditionalInfo } from '../model/additionalInfo';
import { DiscountReservationDTO } from '../model/discountReservation';
import { ReservationDTO } from '../model/reservationDTO';
import { RevisionDTO } from '../model/revisionDTO';
import { ReservationService } from '../service/reservation.service';
import { RevisionService } from '../service/revision.service';
import { ServiceService } from '../service/service.service';
import { SubscriptionService } from '../service/subscription.service';

@Component({
  selector: 'app-action-reservations',
  templateUrl: './action-reservations.component.html',
  styleUrls: ['./action-reservations.component.css']
})
export class ActionReservationsComponent implements OnInit {

  returnData: DiscountReservationDTO[] = new Array();
  dto: ReservationDTO;
  arr: AdditionalInfo[] = new Array();
  id: number;
  start = new Date();
  end = new Date();

  constructor(private service:ServiceService, private route: ActivatedRoute, private resService: ReservationService, private subService: SubscriptionService, private revService: RevisionService) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(params => {
       this.id = params['id'];// you should have your id here.
       
      });

      this.getDiscRes(this.id)
  }


  getDiscRes(id: number){
    this.service.getDiscountReservations(id).subscribe(
      res => {
        
        this.returnData = res;
        console.log(this.returnData)
        this.returnData.forEach(element => {
          // element.startTime = new Date(element.startTime);

          var startyear = element.startTime[0]
          
          
          var startmonth = element.startTime[1]
      
          var startday = element.startTime[2]
          var starthours = element.startTime[3]
          var startminutes = element.startTime[4]
          this.start = new Date(startyear, startmonth-1, startday, starthours, startminutes);
          console.log(element.startTime)
          console.log(this.start)
          var endyear = element.endTime[0]
          
          var endmonth = element.endTime[1]
          var endday = element.endTime[2]
          var endhours = element.endTime[3]
          var endminutes = element.endTime[4]
          
          this.end = new Date(endyear,endmonth-1, endday, endhours, endminutes)
          element.startTime = this.start;
          console.log(element.startTime)
          element.endTime = this.end;
          
        });
        
      }
    )
  }

  reserve(i: number){
    let st = this.returnData[i].startTime;
        console.log(st)
    let et = this.returnData[i].endTime;
    // if(st != null && et != null){
    //   st = st.toLocaleString()
    //   et = et.toLocaleString()
      
    //   st = this.parseDate(st)
    //   et = this.parseDate(et)
    // }
    let price = document.getElementById("price"+i).textContent;
    // let et = document.getElementById("end")
    let id = document.getElementById("id"+i).textContent;
    let capacity = document.getElementById("cap"+i).textContent;
    let discPrice = document.getElementById("discPrice"+i).textContent;
    let city = document.getElementById("city"+i).textContent;
    let addInfo = this.returnData[i].additionalInfos;

    this.resService.reserve(new ReservationDTO(Number(id), st, et, addInfo, this.id, Number(capacity))).subscribe(
      res => {

      }
    )
    

    console.log(addInfo)
    
    // let noGuests = this.searchForm.get('noGuests').value;
    // this.reservationDTO = new ReservationDTO(start,end,this.additionalInfos,this.serviceId, noGuests);
    // this.service.reserve(this.reservationDTO).subscribe(
    //   res => {

    //   }
    // )

    

  }

  parseDate(date:string){
    let parts = date.split(" ")
   
    let dateStr = parts[0]
    let timeStr = parts[1]
    let dateParts = dateStr.split(".")
    let timeParts = timeStr.split(":")

    for(let i = 0; i< dateParts.length; i++){
      dateParts[i].trim()
      
    }
    if(dateParts[0].length === 1){
      dateParts[0] = "0"+dateParts[0]
    }
    
    if(dateParts[1].length === 1){
      dateParts[1] = "0"+dateParts[1]
    }
    let result = "";
    result += dateParts[2]+"-"+dateParts[1]+"-"+dateParts[0]+"T"+timeStr
    result = result.trim();

    return result;
  }




}
