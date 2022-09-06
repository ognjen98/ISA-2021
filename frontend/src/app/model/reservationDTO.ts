import { AdditionalInfo } from "./additionalInfo";

export class ReservationDTO {
	private id: number;
    private start: any;
    private end: any;
    private additionalInfos: AdditionalInfo[];
    private serviceId: number;
    private noPersons: number;


	constructor($id: number, $start: any, $end: any, $additionalInfos: AdditionalInfo[], $serviceId: number, $noPersons: number) {
		this.id = $id;
		this.start = $start;
		this.end = $end;
		this.additionalInfos = $additionalInfos;
		this.serviceId = $serviceId;
		this.noPersons = $noPersons;
	}


}