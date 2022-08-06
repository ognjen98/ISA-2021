import { AdditionalInfo } from "./additionalInfo";

export class GetReservationDTO {
	 id: number;
	 startTime: Date;
	 endTime: Date;
	 maxCapacity: number;
	 price: number;
	 city: string;
	 additionalInfoSet: AdditionalInfo[];


	constructor($id: number, $startTime: Date, $endTime: Date, $maxCapacity: number, $price: number, $city: string, $additionalInfoSet: AdditionalInfo[]) {
		this.id = $id;
		this.startTime = $startTime;
		this.endTime = $endTime;
		this.maxCapacity = $maxCapacity;
		this.price = $price;
		this.city = $city;
		this.additionalInfoSet = $additionalInfoSet;
	}
	

}