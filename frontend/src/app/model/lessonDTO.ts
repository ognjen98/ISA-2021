export class LessonDTO {

    private id: number;
    private name: string;
    private price: number;
    private grade: number;
    private noGuests: number;


	constructor($id: number, $name: string, $price: number, $grade: number, $noGuests: number) {
		this.id = $id;
		this.name = $name;
		this.price = $price;
		this.grade = $grade;
		this.noGuests = $noGuests;
	}

}