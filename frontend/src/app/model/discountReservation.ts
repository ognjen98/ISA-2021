import { AdditionalInfo } from "./additionalInfo";

export class DiscountReservationDTO {
	 id: number;
	 startTime: Date;
	 endTime: Date;
	 maxCapacity: number;
	 price: number;
	 city: string;
	 additionalInfos: AdditionalInfo[];
	 discPrice: number;
	 image: string

	 constructor($id: number, $startTime: Date, $endTime: Date, $maxCapacity: number, $price: number, $city: string, $additionalInfos: AdditionalInfo[], $discPrice: number, image: string) {
		this.id = $id;
		this.startTime = $startTime;
		this.endTime = $endTime;
		this.maxCapacity = $maxCapacity;
		this.price = $price;
		this.city = $city;
		this.additionalInfos = $additionalInfos;
		this.discPrice = $discPrice;
		this.image = image;
	}

}