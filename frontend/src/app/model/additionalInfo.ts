export class AdditionalInfo {
    private id: number;
    private info: string;
    private price: number;


	constructor($id: number, $info: string, $price: number) {
		this.id = $id;
		this.info = $info;
		this.price = $price;
	}
    
}