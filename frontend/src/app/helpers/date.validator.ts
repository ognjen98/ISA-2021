import { AbstractControl, ValidatorFn } from "@angular/forms";

export class CustomeDateValidators {
    static fromToDate(fromDateField: string, toDateField: string, errorName: string = 'fromToDate'): ValidatorFn {
        return (formGroup: AbstractControl): { [key: string]: boolean } | null => {
            const fromDate = formGroup.get(fromDateField).value as Date;
            const toDate = formGroup.get(toDateField).value as Date;
           // Ausing the fromDate and toDate are numbers. In not convert them first after null check
            if ((fromDate !== null && toDate !== null) && fromDate > toDate) {
               
                return {[errorName]: true};
            }
            if(fromDate != null && toDate == null){
                return {["Both dates must be filled"]:true};
            }
            if(fromDate == null && toDate != null){
                return {["Both dates must be filled"]:true};
            }
            return null;
        };
    }
}