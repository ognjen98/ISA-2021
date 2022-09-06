export class CPPDTO {
	points: number;
	category: number;
	penalties: number;

	constructor($points: number, $category: number, $penalties: number) {
		this.points = $points;
		this.category = $category;
		this.penalties = $penalties;
	}

}