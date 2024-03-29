export class ServiceDTO {
	id: number;
	name: string;
	grade: number;
	price: number;
	streetName: string;
	number: string;
	city: string;
	state: string;
	image: string;


	constructor($id: number, $name: string, $grade: number, $price: number, $streetName: string, $number: string, $city: string, $state: string, $image: string) {
		this.id = $id;
		this.name = $name;
		this.grade = $grade;
		this.price = $price;
		this.streetName = $streetName;
		this.number = $number;
		this.city = $city;
		this.state = $state;
		this.image = $image;
	}
	

}