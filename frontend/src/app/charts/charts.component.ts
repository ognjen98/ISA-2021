import { Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ChartConfiguration, ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective, Label } from 'ng2-charts';
import { element } from 'protractor';
import { DayMonthValueDTO } from '../model/dayMontValueDTO';
import { ReportDTO } from '../model/reportDTO';
import { ReservationService } from '../service/reservation.service';


@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit {

  result: number[] = new Array(); 
  options: string[] = ['Year', 'Month', 'Range'];
  values: number[] = new Array();
  days: string[] = new Array();
  days2: string[] = new Array();
  maxDate = new Date();
  reportFormRange: FormGroup;
  reportFormYear: FormGroup;
  reportFormMonth: FormGroup;
  show: number;
  dayMonthValueDTO: DayMonthValueDTO[] = new Array();
  @ViewChildren('outerDiv') outerDiv: QueryList<ElementRef>;
  @ViewChild(BaseChartDirective) chartDirective: BaseChartDirective;

  constructor(private service: ReservationService, private fb: FormBuilder) { }

  ngOnInit(): void {
    
    this.reportFormRange=this.fb.group({
      start: [""],
      end: [""]
      
     
    });

    this.reportFormYear=this.fb.group({
      year: [""],
    
      
     
    });

    this.reportFormMonth=this.fb.group({
      year: [""],
      month: [""]
      
     
    });
    
  }

  checkRadios(event){
    let elem = event.value;
    console.log(elem)
    console.log()
    if(elem === "Year"){
      this.show = 0;

    }
    else if(elem === "Month"){
      this.show = 1;
    }
    else{
      this.show = 2;
    }
  }


  getReport(){
    
    let type;
    if(this.show === 0){
      type = "Year"
      console.log(type)
      let year = this.reportFormYear.get('year').value;
      this.service.getReport(new ReportDTO(type,year,null,null,null)).subscribe(
        res => {
          this.dayMonthValueDTO = res;
          
          console.log(this.values)
          this.dayMonthValueDTO.forEach(element => {
            this.values.push(element.avg)
            console.log(this.values)
            
          })
          this.chartDirective.chart.data.datasets[0].data = this.values
      this.chartDirective.chart.update()
          this.values = new Array();
          console.log(this.values)
        }
      )
      
    }
    else if (this.show === 1){
      type = "Month"
      console.log(type)
      let year = this.reportFormMonth.get('year').value;
      let month = this.reportFormMonth.get('month').value;
      this.service.getReport(new ReportDTO(type,year,month,null,null)).subscribe(
        res => {
          this.dayMonthValueDTO = res;
          
          console.log(this.values)
          this.dayMonthValueDTO.forEach(element => {
            this.values.push(element.value)
            this.days.push(String(element.dayMonth))
            console.log(this.values)
            
          })
          this.chartDirective.chart.data.datasets[0].data = this.values
          this.chartDirective.chart.data.labels = this.days;
      this.chartDirective.chart.update()
          this.values = new Array();
          this.days =  new Array();
          console.log(this.values)
        }
      )
      console.log(type)
    }
    else{
      type = "Range"
      console.log(type)
      let start = this.reportFormRange.get('start').value;
      console.log(start)
      let end = this.reportFormRange.get('end').value;
      start = start.toLocaleString("sr-RS")
      console.log(start)
      end = end.toLocaleString("sr-RS")
      
      start = this.parseDate(start)
      console.log(start)
      end = this.parseDate(end)
      this.service.getReport(new ReportDTO(type,null,null,start,end)).subscribe(
        res => {
          this.dayMonthValueDTO = res;
          
          
          this.dayMonthValueDTO.forEach(element => {
            this.values.push(element.value)
            this.days2.push(String(element.dayMonth))
           
            
          })
          this.chartDirective.chart.data.datasets[0].data = this.values
          this.chartDirective.chart.data.labels = this.days2;
      this.chartDirective.chart.update()
          this.values = new Array();
          this.days2 =  new Array();
          
        }
      )
      console.log(type)
    }
    
  }

  parseDate(date:string){
    let parts = date.split(" ")
   
    let dateStr = parts[0]
    
    let dateParts = dateStr.split(".")

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
    result += dateParts[2]+"-"+dateParts[1]+"-"+dateParts[0]
    result = result.trim();

    return result;
  }

  

  public lineChartType: ChartType = 'line';

  public lineChartOptions: ChartOptions = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: { xAxes: [{}], yAxes: [{
      ticks : {
        beginAtZero : true,
        min : 0,
      }
    }] },
    animation : {
      easing : 'linear',
      duration : 1500
    },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };




  public yearLabels: Label[] = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July' , 'August', 'September', 'October', 'November', 'December']
  public monthLabels: Label[] = this.days
  public rangeLabels: Label[] = this.days2


  public yearData: ChartDataSets[] = [
    { data: this.values,
      label: "year"},
    

  ];

  public monthData: ChartDataSets[] = [
    { data: this.values,
      label: "month"},
    

  ];

  public rangeData: ChartDataSets[] = [
    { data: this.values,
      label: "range"},
    

  ];

  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;
}
