export class SearchDataDTO {
    private startTime: Date;
    private endTime: Date;
    private noGuests: string;
    private entity: string;
    private grade: string;
    private location: string;


	constructor($startTime: Date, $endTime: Date, $noGuests: string, $entity: string, $grade: string, $location: string) {
		this.startTime = $startTime;
		this.endTime = $endTime;
		this.noGuests = $noGuests;
		this.entity = $entity;
		this.grade = $grade;
		this.location = $location;
	}

}