import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { AuthenticationService } from '../../../services/authenticationService';
import { ResponseErrors } from '../../../models/responseErrors';
import { DataService } from '../../../services/dataService';
import { UserAccountService } from '../../../services/userAccountService';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  private _router = inject(Router);
  private _formBuilder = inject(FormBuilder);
  private _authService = inject(AuthenticationService);
  private _userAccountService = inject(UserAccountService);
  private _dataService = inject(DataService);
  responseErrors: ResponseErrors | null = null;
  loginForm: FormGroup;
  showPassword = false;

  constructor() {
    this.loginForm = this._formBuilder.group({
      email: ["", [Validators.required]],
      password: ["", [Validators.required]],
      rememberMe: [false]
    });
  }

  loginUser(): void {
    console.log(this.loginForm.value);
    this._authService.sendLoginRequest(this.loginForm.value).subscribe({
      next: response => {
          localStorage.setItem('token', response.token);
          this.loginForm.reset();

          this.loadUserAccount(response.userAccountId);
          this._router.navigate(['/app']);
      },
      error: e => {
        this.responseErrors = e.error;
        console.error(this.responseErrors);
      }
    });
    
    console.log("Login form submitted", this.loginForm.value);
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  loadUserAccount(id: number): void {
    this._userAccountService.getById(id).subscribe({
      next: ua => this._dataService.setLoggedUser(ua),
      error: e => console.error("Error loading user account:", e)
    });
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
