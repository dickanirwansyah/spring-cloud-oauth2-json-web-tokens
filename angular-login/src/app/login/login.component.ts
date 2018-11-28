import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private authservice: AuthService
  ) { }

  ngOnInit() {
  }

  public loginData = {

    username : "",
    password : ""

  }

  /** login */
  login(){
    this.authservice.getOauth2(this.loginData)
  }

}
