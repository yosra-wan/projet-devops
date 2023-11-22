import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthentificationService } from '../../services/authentification.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  registerForm: FormGroup;
  password: String = '';
  firstname: String = '';
  lastname: String = '';
  username: String = '';
  email: String = '';
  phone: String = '';
  isInvalid: any;
  isValid: any;
  isSubmitted = false;
  user = {
    password: '',
    firstname: '',
    lastname: '',
    username: '',
    email: '',
    phone: '',
  };
  items = Object.keys(this.user);

  constructor(
    private authServ: AuthentificationService,
    private router: Router
  ) {
    this.registerForm = new FormGroup({
      password: new FormControl('', [
        Validators.required,
        this.noWhiteSpaceValidator,
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.email,
        this.noWhiteSpaceValidator,
      ]),
      phone: new FormControl('', [
        Validators.required,
        Validators.pattern('^[295]\\d{7}$'),
      ]),

      firstname: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        this.noWhiteSpaceValidator,
      ]),
      lastname: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        this.noWhiteSpaceValidator,
      ]),
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
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
    const loginControl = this.registerForm.get(fieldName);
    this.isInvalid = loginControl?.invalid && loginControl.touched;
    return this.isInvalid;
  }

  isFieldValid(fieldName: string) {
    const loginControl = this.registerForm.get(fieldName);
    this.isValid = loginControl?.valid && loginControl.touched;
    return this.isValid;
  }

  signup() {
    this.isSubmitted = true;
    const user = this.registerForm.value;
    user.phone = '+216' + user.phone;

    if (this.registerForm.invalid) {
      alert('verifier les champs');
    } else {
      this.authServ.register(user).subscribe({
        next: (data) => {
          localStorage.setItem('token', data['token']);
          console.log('Login successful', data);
          this.router.navigate(['/carmanagement']);
        },
        error: (err) => {
          console.log('Error during the authentication process', err);
          // alert(err);
        },
      });
    }
  }
}
