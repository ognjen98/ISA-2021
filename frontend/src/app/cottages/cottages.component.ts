import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomeDateValidators } from '../helpers/date.validator';
import { AdditionalInfo } from '../model/additionalInfo';
import { CottageDTO } from '../model/cottageDTO';
import { InhrCottageDTO } from '../model/inhrCottageDTO';
import { InhrShipDTO } from '../model/inhrShipDTO';
import { ReservationDTO } from '../model/reservationDTO';
import { SearchDataDTO } from '../model/searchDataDTO';
import { ServiceDTO } from '../model/serviceDTO';
import { SortDTO } from '../model/sortDTO';
import { SortDTOCottage } from '../model/sortDTOCottage';
import { ReservationService } from '../service/reservation.service';
import { ServiceService } from '../service/service.service';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-cottages',
  templateUrl: './cottages.component.html',
  styleUrls: ['./cottages.component.css']
})
export class CottagesComponent implements OnInit {

  // returnData: ServiceDTO[];
  // sortDTO: SortDTO;
  // constructor(private service: ServiceService, private service2: ReservationService) { }

  // ngOnInit(): void {
  //   this.getCottages();
  // }


  // getCottages(){
  //   this.service.getCottages().subscribe(
  //     res => {
  //       this.returnData = res;
  //     }
  //   )
  // }

  // sort(event){
  //   let sortParam = event.value;
  //   console.log(event.value)
  //   this.sortDTO = new SortDTO(this.returnData, sortParam)
  //   this.service2.sort(this.sortDTO).subscribe(
  //     res =>{
  //       this.returnData = res;
  //       console.log(this.returnData)
  //     }
  //   )
  // }

  searchForm: FormGroup;
  today: Date;
  minDate = new Date();
  dto: SearchDataDTO;
  filterForm: FormGroup;
  returnData: ServiceDTO[] = new Array();
  returnData2: InhrCottageDTO[] = new Array();
  returnData3: ServiceDTO[] = new Array();
  sortDTO: SortDTO;
  additionalInfos: AdditionalInfo[] = new Array();
  reservationDTO: ReservationDTO;
  type: string = "COTTAGE";
  serviceId: number;
  filtered: boolean = false;
  filteredData: InhrShipDTO[] = new Array();
  searched: boolean = false;
  role: any;


  
  
  constructor(private fb: FormBuilder,private service: ReservationService, private serviceService: ServiceService, private tokenService: TokenService) {
    
   }

  ngOnInit(): void {

    this.filterForm = this.fb.group({
      type: []
    })

    this.searchForm=this.fb.group({
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

    this.getRole();
    this.getCottages();
  }

   getCottages(){
    this.serviceService.getCottages().subscribe(
      res => {
        this.returnData = res;
      }
    )
  }

  getRole(){
    this.role = this.tokenService.getRole();
  }


  goBack(){
    this.searched = false;
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
    if(this.type === "COTTAGE"){
      if(this.searched === false){
        let dto = new SortDTO(this.returnData, sortParam)
        
        this.service.sort(dto).subscribe(
          res =>{
            this.returnData = res;
            
          }
        )
      }
      else{

          let dto = new SortDTOCottage(this.returnData2, sortParam)
          
          this.service.sortCottages(dto).subscribe(
            res =>{
              this.returnData2 = res;
              
            }
          )
        
        
      }
    }
    
    
    
  }

  search(){
    this.searched = true;
    let entity = "COTTAGE"
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
    if(entity === "COTTAGE"){
      this.service.searchCottages(this.dto).subscribe(
        res =>
        {
          this.returnData2 = res;
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
