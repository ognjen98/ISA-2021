import { ServiceDTO } from "./serviceDTO";

export class InhrCottageDTO extends ServiceDTO{
    private noGuests: number;
    private noRooms: number;
    private noBedsByRoom: number;


	constructor(id:number, name: string, grade: number, price:number, streetName:string, number: string, city: string, state: string, $noGuests: number, $noRooms: number, $noBedsByRoom: number, image: string) {
        super(id, name, grade, price, streetName, number, city, state, image);
		this.noGuests = $noGuests;
		this.noRooms = $noRooms;
		this.noBedsByRoom = $noBedsByRoom;
	}


    
}