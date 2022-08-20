export class AdminDTO {
     email: string;
     name: string;
     surname: string;
     password: string;


	constructor($email: string, $name: string, $surname: string, $password: string) {
		this.email = $email;
		this.name = $name;
		this.surname = $surname;
		this.password = $password;
	}

    
}