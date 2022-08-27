export class UserDTO {
     id: number;
     email: string;
     name: string;
     surname: string;

	constructor($id: number, $email: string, $name: string, $surname: string) {
		this.id = $id;
		this.email = $email;
		this.name = $name;
		this.surname = $surname;
	}

}