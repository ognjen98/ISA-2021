import { InhrShipDTO } from "./inhrShipDTO";

export class SortDTOShip {
    private dto: InhrShipDTO[];
    private sortParam: string;

	constructor($dto: InhrShipDTO[], $sortParam: string) {
		this.dto = $dto;
		this.sortParam = $sortParam;
	}

}