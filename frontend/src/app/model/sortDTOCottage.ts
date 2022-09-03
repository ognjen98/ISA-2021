import { InhrCottageDTO } from "./inhrCottageDTO";

export class SortDTOCottage {
    private dto: InhrCottageDTO[];
    private sortParam: string;

	constructor($dto: InhrCottageDTO[], $sortParam: string) {
		this.dto = $dto;
		this.sortParam = $sortParam;
	}

}