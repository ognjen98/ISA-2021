export class Category{
    private id: number;
    private name: string;
    private discount: number;
    private points: number;
    private type: string;


	constructor($id: number, $name: string, $discount: number, $points: number, $type: string) {
		this.id = $id;
		this.name = $name;
		this.discount = $discount;
		this.points = $points;
		this.type = $type;
	}

}