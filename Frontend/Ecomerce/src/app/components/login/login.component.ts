import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { LoginModel } from 'src/app/models/login.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginModel:LoginModel;
  private token:any;
  private identity:any;

  constructor(
    private loginService: LoginService
  ) {

    this.loginModel = new LoginModel('', '');
    this.token = '';

   }

  ngOnInit(): void {

    this.Login();

  }

  Login(){

    this.loginModel.user_name = "Default";
    this.loginModel.password = "1234"

    this.loginService.getLogin(this.loginModel).subscribe(
      response => {

        if(response.status != 'error'){

           this.token = response.jwt_token;
           localStorage.setItem('token', this.token);

           this.loginService.getLogin(this.loginModel, true).subscribe(

             response => {

                this.identity = response;
                localStorage.setItem('identity', JSON.stringify(this.identity));

             },
             error => {

               console.log(error);

             }

             );

        }

       },

      error =>{

        console.log(error.error.message);

      }

    );

  }
}
