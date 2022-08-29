export class RevisionDTO {
    private id: number;
    private grade: number;
    private text: string;
    private type: string;
    private serviceId: number;


	constructor($id: number, $grade: number, $text: string, $type: string, $serviceId: number) {
		this.id = $id;
		this.grade = $grade;
		this.text = $text;
		this.type = $type;
		this.serviceId = $serviceId;
	}

}