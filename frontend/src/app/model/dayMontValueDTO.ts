export class DayMonthValueDTO{
	 value: number;
	 dayMonth: number;
	 count: number;
	 avg: number;


	constructor($value: number, $dayMonth: number, $count: number, $avg: number) {
		this.value = $value;
		this.dayMonth = $dayMonth;
		this.count = $count;
		this.avg = $avg;
	}

}