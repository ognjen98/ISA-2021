export class ResActionDTO {
    private price: number;
    private discount: number;
    private serviceId: number;


	constructor($price: number, $discount: number, $serviceId: number) {
		this.price = $price;
		this.discount = $discount;
		this.serviceId = $serviceId;
	}

}