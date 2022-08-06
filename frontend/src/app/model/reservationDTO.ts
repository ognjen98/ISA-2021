import { AdditionalInfo } from "./additionalInfo";

export class ReservationDTO {
	private id: number;
    private start: Date;
    private end: Date;
    private additionalInfos: AdditionalInfo[];
    private serviceId: number;
    private noPersons: number;


	constructor($id: number, $start: Date, $end: Date, $additionalInfos: AdditionalInfo[], $serviceId: number, $noPersons: number) {
		this.id = $id;
		this.start = $start;
		this.end = $end;
		this.additionalInfos = $additionalInfos;
		this.serviceId = $serviceId;
		this.noPersons = $noPersons;
	}


}