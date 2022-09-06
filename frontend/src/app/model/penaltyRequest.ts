export class PenaltyRequest {
    id: number;

    text: string;

    status: number;

    clientId: number;

    sellerId: number;

	constructor($id: number, $text: string, $status: number, $clientId: number, $sellerId: number) {
		this.id = $id;
		this.text = $text;
		this.status = $status;
		this.clientId = $clientId;
		this.sellerId = $sellerId;
	}

}