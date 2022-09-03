import { ServiceDTO } from "./serviceDTO";

export class InhrShipDTO extends ServiceDTO{
    private noGuests: number;
    private type: string;
    private length: number;
    private noEngines: number;
    private maxSpeed: number;
    private enginePower: number;


	constructor(id:number, name: string, grade: number, price:number, streetName:string, number: string, city: string, state: string, $noGuests: number, $type: string, $length: number, $noEngines: number, $maxSpeed: number, $enginePower: number) {
        super(id, name, grade, price, streetName, number, city, state);
		this.noGuests = $noGuests;
		this.type = $type;
		this.length = $length;
		this.noEngines = $noEngines;
		this.maxSpeed = $maxSpeed;
		this.enginePower = $enginePower;
	}

}