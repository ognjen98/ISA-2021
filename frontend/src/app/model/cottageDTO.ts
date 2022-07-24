export class CottageDTO {

    private id: number;
    private name: string;
    private price: number;
    private grade: number;
    private noGuests: number;
    private noRooms: number;
    private noBedsByRoom: number;


	constructor($id: number, $name: string, $price: number, $grade: number, $noGuests: number, $noRooms: number, $noBedsByRoom: number) {
		this.id = $id;
		this.name = $name;
		this.price = $price;
		this.grade = $grade;
		this.noGuests = $noGuests;
		this.noRooms = $noRooms;
		this.noBedsByRoom = $noBedsByRoom;
	}

}