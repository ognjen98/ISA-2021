import { Component, OnInit } from '@angular/core';
import { LessonDTO } from '../model/lessonDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { SortDTO } from '../model/sortDTO';
import { ReservationService } from '../service/reservation.service';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-lessons',
  templateUrl: './lessons.component.html',
  styleUrls: ['./lessons.component.css']
})
export class LessonsComponent implements OnInit {

  returnData: ServiceDTO[];
  sortDTO: SortDTO;
  constructor(private service: ServiceService, private service2: ReservationService) { }

  ngOnInit(): void {
    this.getLessons();
  }


  getLessons(){
    this.service.getLessons().subscribe(
      res => {
        this.returnData = res;
      }
    )
  }

  sort(event){
    let sortParam = event.value;
    console.log(event.value)
    this.sortDTO = new SortDTO(this.returnData, sortParam)
    this.service2.sort(this.sortDTO).subscribe(
      res =>{
        this.returnData = res;
        console.log(this.returnData)
      }
    )
  }
}
