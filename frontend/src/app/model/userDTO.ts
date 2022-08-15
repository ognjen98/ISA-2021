export class UserDTO {
    private id: number;
    private email: string;
    private name: string;
    private surname: string;

	constructor($id: number, $email: string, $name: string, $surname: string) {
		this.id = $id;
		this.email = $email;
		this.name = $name;
		this.surname = $surname;
	}

}