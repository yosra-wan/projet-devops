import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { AuthentificationService } from '../../services/authentification.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  password: String = '';
  login: String = '';
  loginForm: FormGroup;
  isSubmitted = false;
  isInvalid: any;
  isValid: any;
  // isPasswordInvalid = false;

  constructor(
    private authServ: AuthentificationService,
    private router: Router
  ) {
    this.loginForm = new FormGroup({
      password: new FormControl('', [
        Validators.required,
        this.noWhiteSpaceValidator,
      ]),
      login: new FormControl('', [
        Validators.required,
        this.noWhiteSpaceValidator,
      ]),
    });
  }

  noWhiteSpaceValidator(ctrl: FormControl): { [s: string]: boolean } | null {
    if ((ctrl.value as string).indexOf(' ') >= 0) {
      return { cannotContainSpace: true };
    }

    return null;
  }

  isFieldInvalid(fieldName: string) {
    const loginControl = this.loginForm.get(fieldName);
    this.isInvalid = loginControl?.invalid && loginControl.touched;
    return this.isInvalid;
  }

  isFieldValid(fieldName: string) {
    const loginControl = this.loginForm.get(fieldName);
    this.isValid = loginControl?.valid && loginControl.touched;
    return this.isValid;
  }

  cnx() {
    this.isSubmitted = true;
    const credentials = this.loginForm.value;
    console.log(this.loginForm.value);

    if (this.loginForm.invalid) {
      alert('verifier les champs');
    } else {
      this.authServ.login(credentials).subscribe({
        next: (data) => {
          localStorage.setItem('token', data['token']);
          console.log('Login successful', data);
          this.router.navigate(['/carmanagement']);
        },
        error: (err) => {
          console.log('Error during the authentication process', err.error),
            alert(err.error);
        },
      });
    }
  }
}
