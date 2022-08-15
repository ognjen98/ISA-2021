import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../model/userDTO';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

  data: UserDTO[] = new Array();
  constructor(private service: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }


  getUsers(){
    this.service.getUsers().subscribe(
      res => {
        this.data = res;

      }
    )
  }

  delete(id: number){
    this.service.delete(id).subscribe(
      res => {
          this.getUsers()
      }
    )
  }

}
