import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../services/authenticationService';
import { ResponseErrors } from '../../../models/responseErrors';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  @Output() switchToLogin = new EventEmitter<void>();
  private _formBuilder = inject(FormBuilder);
  private _authService = inject(AuthenticationService);
  responseErrors: ResponseErrors | null = null;
  registerForm: FormGroup;
  showPassword = false;
  showRepeatedPassword = false;
  passwordMismatch = false;

  constructor() {
    this.registerForm = this._formBuilder.group({
      email: ["", [Validators.required, this.checkEmail]],
      username: ["", [Validators.required]],
      password: ["", [Validators.required, this.checkPassword]],
      repeatedPassword: ["", [Validators.required]]
    });

    this.registerForm.get('email')?.valueChanges.subscribe(emailValue => {
      const username = this.generateUsername(emailValue);
      this.registerForm.get('username')?.setValue(username);
    });
  }

  registerUser(): void {
    this._authService.sendRegisterRequest(this.registerForm.value).subscribe({
      next: () => {
          this.registerForm.reset();
          this.switchToLogin.emit();
        },
      error: e => this.responseErrors = e.error
    });
  }

  generateUsername(email: string): string {
    const username = email?.split('@')[0];
    if (username?.length > 16) {
      return username.substring(0, 16);
    }
    
    return username;
  }

  checkEmail(control: FormControl)  {
    const email = control.value;
    const regExp: RegExp =  /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    
    if (!regExp.test(email)) {
      return { invalidFormat: true }
    }
    
    return null;
  }

  checkPassword(control: FormControl) {
    const password = control.value;

    if (password?.length < 8 || password?.length > 16) {
      return { lengthError: true };
    }

    if (!/[A-Z]/.test(password)) {
      return { uppercaseError: true };
    }

    if (!/[0-9]/.test(password)) {
      return { numberError: true };
    }

    if (!/[^a-zA-Z0-9]/.test(password)) {
      return { specialCharError: true };
    }

    return null;
  }

  checkPasswordMismatch(): void {
    const password = this.registerForm?.get('password')?.value;
    const repeatedPassword = this.registerForm?.get('repeatedPassword')?.value;

    if (password !== repeatedPassword) {
      this.passwordMismatch = true;
    } else {
      this.passwordMismatch = false;
    }
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }

  toggleRepeatedPasswordVisibility() {
    this.showRepeatedPassword = !this.showRepeatedPassword;
  }

  get email() {
    return this.registerForm.get('email');
  }

  get username() {
    return this.registerForm.get('username');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get repeatedPassword() {
    return this.registerForm.get('repeatedPassword');
  }
}
