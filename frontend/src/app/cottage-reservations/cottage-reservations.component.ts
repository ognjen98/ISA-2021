import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdditionalInfo } from '../model/additionalInfo';
import { GetReservationDTO } from '../model/getReservationDTO';
import { ReservationDTO } from '../model/reservationDTO';
import { ReservationService } from '../service/reservation.service';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-cottage-reservations',
  templateUrl: './cottage-reservations.component.html',
  styleUrls: ['./cottage-reservations.component.css']
})
export class CottageReservationsComponent implements OnInit {

  returnData: GetReservationDTO[] = new Array();
  dto: ReservationDTO;
  arr: AdditionalInfo[] = new Array();
  id: number;
  diff: number[] = new Array();
  start = new Date();
  end = new Date();

  constructor(private service:ServiceService, private route: ActivatedRoute, private resService: ReservationService) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(params => {
       this.id = params['id'];// you should have your id here.
       
      });

      this.getRes()
  }


  getRes(){
    this.resService.getCottageReservations().subscribe(
      res => {
        let i = 0
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
          let difference = this.start.getTime() - Date.now();
          let days = difference / (1000 * 3600 * 24);
          console.log(days)
          this.diff.push(days)
          element.startTime = this.start;
          console.log(element.startTime)
          element.endTime = this.end;
          i++;
        });
        
      }
    )
  }

}
