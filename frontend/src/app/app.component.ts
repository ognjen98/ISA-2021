import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  text: string;
  constructor(private http : HttpClient) { }

  getText(){
    this.test().subscribe(data =>{
      this.text = data;
    });
  }

  test() : Observable<string>{
    return this.http.get<string>("http://localhost:8082/test/define");
  }
}
