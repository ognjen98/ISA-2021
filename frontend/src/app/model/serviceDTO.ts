export class ServiceDTO {
    private name: string;
    private grade: number;
    private price: number;
    private streetName: string;
    private number: string;
    private city: string;
    private state: string;

	constructor($name: string, $grade: number, $price: number, $streetName: string, $number: string, $city: string, $state: string) {
		this.name = $name;
		this.grade = $grade;
		this.price = $price;
		this.streetName = $streetName;
		this.number = $number;
		this.city = $city;
		this.state = $state;
	}

}