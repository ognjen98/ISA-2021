export class SubscriptionDTO {
     id: number;
     serviceName: string;
     city: string;


	constructor($id: number, $serviceName: string, $city: string) {
		this.id = $id;
		this.serviceName = $serviceName;
		this.city = $city;
	}

}