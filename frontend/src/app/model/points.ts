export class Points{
    private id: number;
    private clientPoints: number;
    private sellerPoints: number;


	constructor($id: number, $clientPoints: number, $sellerPoints: number) {
		this.id = $id;
		this.clientPoints = $clientPoints;
		this.sellerPoints = $sellerPoints;
	}
    
}