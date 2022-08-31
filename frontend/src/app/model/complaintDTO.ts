export class ComplaintDTO {
    private id: number;
    private text: string;
    private type: string;
    private serviceId: number;


	constructor($id: number, $text: string, $type: string, $serviceId: number) {
		this.id = $id;
		this.text = $text;
		this.type = $type;
		this.serviceId = $serviceId;
	}

}