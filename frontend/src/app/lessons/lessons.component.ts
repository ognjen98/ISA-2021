import { Component, OnInit } from '@angular/core';
import { LessonDTO } from '../model/lessonDTO';
import { ServiceService } from '../service/service.service';

@Component({
  selector: 'app-lessons',
  templateUrl: './lessons.component.html',
  styleUrls: ['./lessons.component.css']
})
export class LessonsComponent implements OnInit {

  returnData: LessonDTO[];
  constructor(private service: ServiceService) { }

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
}
