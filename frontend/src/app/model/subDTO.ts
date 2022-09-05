export class SubscriptionDTO {
     id: number;
     serviceName: string;
     city: string;
	 image: string


	constructor($id: number, $serviceName: string, $city: string, image: string) {
		this.id = $id;
		this.serviceName = $serviceName;
		this.city = $city;
		this.image = image;
	}

}