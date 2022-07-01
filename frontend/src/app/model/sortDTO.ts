import { ServiceDTO } from "./serviceDTO";

export class SortDTO {
    private dto: ServiceDTO[];
    private sortParam: string;

	constructor($dto: ServiceDTO[], $sortParam: string) {
		this.dto = $dto;
		this.sortParam = $sortParam;
	}

    
}