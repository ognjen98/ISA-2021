import { InhrShipDTO } from "./inhrShipDTO";

export class FilterDTO {
    private entity: string;
    private type: string;
    private dtos: InhrShipDTO[];


	constructor($entity: string, $type: string, $dtos: InhrShipDTO[]) {
		this.entity = $entity;
		this.type = $type;
		this.dtos = $dtos;
	}

}