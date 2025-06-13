import { Component } from '@angular/core';
import { LoginComponent } from "../login/login.component";
import { RegisterComponent } from "../register/register.component";

@Component({
  selector: 'app-auth-main',
  imports: [LoginComponent, RegisterComponent],
  templateUrl: './auth-main.component.html',
  styleUrl: './auth-main.component.css'
})
export class AuthMainComponent {
  showLoginForm = true;

  toggleLoginForm() {
    this.showLoginForm = !this.showLoginForm;
  }
}
