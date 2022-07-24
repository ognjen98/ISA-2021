export class ShipDTO {
    private id: number;
    private name: string;
    private price: number;
    private grade: number;
    private noGuests: number;
    private type: string;
    private length: number;
    private noEngines: number;
    private maxSpeed: number;
    private enginePower: number;

	constructor($id: number, $name: string, $price: number, $grade: number, $noGuests: number, $type: string, $length: number, $noEngines: number, $maxSpeed: number, $enginePower: number) {
		this.id = $id;
		this.name = $name;
		this.price = $price;
		this.grade = $grade;
		this.noGuests = $noGuests;
		this.type = $type;
		this.length = $length;
		this.noEngines = $noEngines;
		this.maxSpeed = $maxSpeed;
		this.enginePower = $enginePower;
	}
    
}