import { NgxMatDateFormats, NGX_MAT_DATE_FORMATS } from '@angular-material-components/datetime-picker';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { CustomeDateValidators } from '../helpers/date.validator';
import { AdditionalInfo } from '../model/additionalInfo';
import { FilterDTO } from '../model/filterDTO';
import { InhrCottageDTO } from '../model/inhrCottageDTO';
import { InhrShipDTO } from '../model/inhrShipDTO';
import { ReservationDTO } from '../model/reservationDTO';
import { SearchDataDTO } from '../model/searchDataDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { SortDTO } from '../model/sortDTO';
import { SortDTOCottage } from '../model/sortDTOCottage';
import { SortDTOShip } from '../model/sortDTOShip';
import { ReservationService } from '../service/reservation.service';





@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
})
export class ReservationComponent implements OnInit {

  searchForm: FormGroup;
  today: Date;
  minDate = new Date();
  dto: SearchDataDTO;
  filterForm: FormGroup;
  returnData: ServiceDTO[] = new Array();
  returnData2: InhrCottageDTO[] = new Array();
  returnData3: InhrShipDTO[] = new Array();
  sortDTO: SortDTO;
  additionalInfos: AdditionalInfo[] = new Array();
  reservationDTO: ReservationDTO;
  type: string = "SHIP";
  serviceId: number;
  filtered: boolean = false;
  filteredData: InhrShipDTO[] = new Array();


  
  
  constructor(private fb: FormBuilder,private service: ReservationService) {
    
   }

  ngOnInit(): void {

    this.filterForm = this.fb.group({
      type: []
    })

    this.searchForm=this.fb.group({
      entity: ["SHIP", ],
      location: ["", ],
      noGuests: ["", ],
      grade: ["", ],
      start: ["", Validators.required],
      end: ["", Validators.required]
      
     
    }, { validator: [
      //Default error with this validator:  {fromToDate: true}
      CustomeDateValidators.fromToDate('start', 'end')
      
      // For custome error name like: {customeErrorName: true}, pass third optional parameter with custome name
      // CustomeDateValidators.fromToDate('fromDate', 'toDate', 'customeErrorName')
    ]});
    
    this.searchForm.get('start').valueChanges
    .subscribe(x => {
      let date = x as Date;
      
      let da = date.toLocaleString("sr-RS")
      console.log(da)
      console.log(this.parseDate(da));
    //  // date.setDate(date.getDate()+1);
      
    //   console.log(this.searchForm.get('start').value)
      
      
    //   this.searchForm.get('end').setValue(date);
    //   console.log(this.searchForm.get('end').value)
      
    })

    this.searchForm.get('end').valueChanges
    .subscribe(x => {
      let date = x as Date;
      
      let da = date.toLocaleString()
     
      this.parseDate(da);
    //  // date.setDate(date.getDate()+1);
      
    //   console.log(this.searchForm.get('start').value)
      
      
    //   this.searchForm.get('end').setValue(date);
    //   console.log(this.searchForm.get('end').value)
      
    })

    this.searchForm.get('entity').valueChanges.subscribe(
      x => {
        this.type = x;
        console.log(this.type);
      }
    )

 
  }


  filter(){
    let type = this.filterForm.get('type').value;
    this.filtered = true;
    if(type === "" || type === null){
      this.filtered = false;
      return;
    }

    this.service.filter(new FilterDTO(this.type, type, this.returnData3)).subscribe(
      res => {
        this.filteredData = res;
      }
    )

  }

  

  getInfos(serviceId: number){
    this.serviceId = serviceId;
    this.service.getInfos(serviceId).subscribe(

      res => {
        this.additionalInfos = res
      }
    )
  }

  reserve(){
    let start = this.searchForm.get('start').value;
    let end = this.searchForm.get('end').value;
    if(start != "" && end != ""){
      start = start.toLocaleString("sr-RS")
      end = end.toLocaleString("sr-RS")
      
      start = this.parseDate(start)
      end = this.parseDate(end)
    }
    let noGuests = this.searchForm.get('noGuests').value;
    this.reservationDTO = new ReservationDTO(null,start,end,this.additionalInfos,this.serviceId, noGuests);
    this.service.reserve(this.reservationDTO).subscribe(
      res => {

      }
    )

  }

  sort(event){
    let sortParam = event.value;
    console.log(event.value)
    if(this.type === "SHIP"){
      if(this.filtered === false){
        let dto = new SortDTOShip(this.returnData3, sortParam)
        
        this.service.sortShips(dto).subscribe(
          res =>{
            this.returnData3 = res;
            
          }
        )
      }
      else{
        let dto = new SortDTOShip(this.filteredData, sortParam)
        
        this.service.sortShips(dto).subscribe(
          res =>{
            this.returnData3 = res;
            
          }
        )
      }
    }
    else if(this.type === "COTTAGE"){
      let dto = new SortDTOCottage(this.returnData2, sortParam)
      
      this.service.sortCottages(dto).subscribe(
        res =>{
          this.returnData2 = res;
          
        }
      )
    }
    else {
      this.sortDTO = new SortDTO(this.returnData, sortParam)
      this.service.sort(this.sortDTO).subscribe(
        res =>{
          this.returnData = res;
          console.log(this.returnData)
        }
      )
    }
    
    
  }

  search(){
    let entity = this.searchForm.get('entity').value
    let location = this.searchForm.get('location').value
    let noGuests = this.searchForm.get('noGuests').value
    let grade = this.searchForm.get('grade').value
    let start = this.searchForm.get('start').value
    let end = this.searchForm.get('end').value
    if(start != "" && end != ""){
      start = start.toLocaleString("sr-RS")
      end = end.toLocaleString("sr-RS")
      
      start = this.parseDate(start)
      end = this.parseDate(end)
    }
    this.dto = new SearchDataDTO(start, end, noGuests, entity, grade, location)
    if(entity === "SHIP"){
      this.service.searchShips(this.dto).subscribe(
        res =>
        {
          this.returnData3 = res;
          console.log(this.returnData)
        }
      )
    }
    else if(entity === "COTTAGE"){
      this.service.searchCottages(this.dto).subscribe(
        res =>
        {
          this.returnData2 = res;
          console.log(this.returnData)
        }
      )
    }
    else{
      this.service.searchLessons(this.dto).subscribe(
        res =>
        {
          this.returnData = res;
          console.log(this.returnData)
        }
      )
    }
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

function dateValidator(searchForm: FormGroup): ValidationErrors | null {
  const fromCtrl = searchForm.get('start');
  const toCtrl = searchForm.get('end');

  return new Date(fromCtrl.value) > new Date(toCtrl.value) ? { message: 'Start date must be earlier' } : null;
}




