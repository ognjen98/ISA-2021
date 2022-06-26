import { NgxMatDateFormats, NGX_MAT_DATE_FORMATS } from '@angular-material-components/datetime-picker';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { CustomeDateValidators } from '../helpers/date.validator';
import { SearchDataDTO } from '../model/searchDataDTO';
import { ServiceDTO } from '../model/serviceDTO';
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
  returnData: ServiceDTO;


  
  
  constructor(private fb: FormBuilder,private service: ReservationService) {
    
   }

  ngOnInit(): void {

    this.searchForm=this.fb.group({
      entity: ["SHIP", ],
      location: ["", ],
      noGuests: ["", ],
      grade: ["", ],
      start: [""],
      end: [""]
      
     
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
      console.log(da);
      this.parseDate(da);
    //  // date.setDate(date.getDate()+1);
      
    //   console.log(this.searchForm.get('start').value)
      
      
    //   this.searchForm.get('end').setValue(date);
    //   console.log(this.searchForm.get('end').value)
      
    })

    this.searchForm.get('end').valueChanges
    .subscribe(x => {
      let date = x as Date;
      
      let da = date.toLocaleString()
      console.log(da);
      this.parseDate(da);
    //  // date.setDate(date.getDate()+1);
      
    //   console.log(this.searchForm.get('start').value)
      
      
    //   this.searchForm.get('end').setValue(date);
    //   console.log(this.searchForm.get('end').value)
      
    })

 
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
    this.service.search(this.dto).subscribe(
      res =>
      {
        this.returnData = res;
      }
    )
  }

  parseDate(date:string){
    let parts = date.split(" ")
    console.log(parts[0])
    console.log(parts[1])
    let dateStr = parts[0]
    let timeStr = parts[1]
    let dateParts = dateStr.split(".")
    let timeParts = timeStr.split(":")

    for(let i = 0; i< dateParts.length; i++){
      dateParts[i].trim()
      
    }
    let result = "";
    result += dateParts[2]+"-"+dateParts[1]+"-"+dateParts[0]+"T"+timeStr
    result = result.trim();
    console.log(result)
    console.log(dateParts[0])
    console.log(dateParts[1])
    console.log(dateParts[2])
    console.log(timeParts[0])
    console.log(timeParts[1])
    console.log(timeParts[2])
    return result;
  }
  

}

function dateValidator(searchForm: FormGroup): ValidationErrors | null {
  const fromCtrl = searchForm.get('start');
  const toCtrl = searchForm.get('end');

  return new Date(fromCtrl.value) > new Date(toCtrl.value) ? { message: 'Start date must be earlier' } : null;
}




