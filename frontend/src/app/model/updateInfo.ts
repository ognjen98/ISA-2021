export class UpdateInfoDTO {
    name: string;
    surname: string;
    password: string;
    city: string;
    state: string;
    streetName: string;
    number: string;
    mobile: string;

    constructor(name: string, surname: string, password: string, city: string, state: string, streetName: string, number: string, mobile: string){
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.city = city;
        this.state = state;
        this.streetName = streetName;
        this.number = number;
        this.mobile = mobile;
    }
    
}