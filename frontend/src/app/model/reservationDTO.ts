import { AdditionalInfo } from "./additionalInfo";

export class ReservationDTO {
    private start: Date;
    private end: Date;
    private additionalInfos: AdditionalInfo[];
    private serviceId: number;
    private noPersons: number;


	constructor($start: Date, $end: Date, $additionalInfos: AdditionalInfo[], $serviceId: number, $noPersons: number) {
		this.start = $start;
		this.end = $end;
		this.additionalInfos = $additionalInfos;
		this.serviceId = $serviceId;
		this.noPersons = $noPersons;
	}

}