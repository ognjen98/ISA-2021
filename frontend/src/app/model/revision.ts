export class Revision {
    private id: number;
    private grade: number;
    private text: string;


	constructor($id: number, $grade: number, $text: string) {
		this.id = $id;
		this.grade = $grade;
		this.text = $text;
	}

}