export class ReportDTO {
    private type: string;
    private year: number;
    private month: number;
    private startTime: Date;
    private endTime: Date;


	constructor($type: string, $year: number, $month: number, $startTime: Date, $endTime: Date) {
		this.type = $type;
		this.year = $year;
		this.month = $month;
		this.startTime = $startTime;
		this.endTime = $endTime;
	}

}