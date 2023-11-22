import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthentificationService } from '../../services/authentification.service';

@Component({
  selector: 'app-add-driver',
  templateUrl: './add-driver.component.html',
  styleUrls: ['./add-driver.component.scss'],
})
export class AddDriverComponent {
  DriverForm: FormGroup;
  password: String = '';
  firstname: String = '';
  lastname: String = '';
  username: String = '';
  email: String = '';
  phone: String = '';
  hourRate: Number | undefined;
  isInvalid: any;
  isValid: any;
  isSubmitted = false;
  displayStyle = 'none';
  visible = false;
  driverId: any;
  constructor(
    private authServ: AuthentificationService,
    private router: Router
  ) {
    this.DriverForm = new FormGroup({
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
      hourRate: new FormControl('', [Validators.required]),
    });
  }

  noWhiteSpaceValidator(ctrl: FormControl): { [s: string]: boolean } | null {
    if ((ctrl.value as string).indexOf(' ') >= 0) {
      return { cannotContainSpace: true };
    }

    return null;
  }

  isFieldInvalid(fieldName: string) {
    const loginControl = this.DriverForm.get(fieldName);
    this.isInvalid = loginControl?.invalid && loginControl.touched;
    return this.isInvalid;
  }

  isFieldValid(fieldName: string) {
    const loginControl = this.DriverForm.get(fieldName);
    this.isValid = loginControl?.valid && loginControl.touched;
    return this.isValid;
  }

  signup() {
    this.isSubmitted = true;
    const user = this.DriverForm.value;

    console.log('user', user);

    user.phone = '+216' + user.phone;

    if (this.DriverForm.invalid) {
      alert('verifier les champs');
    } else {
      this.authServ.addDriver(user).subscribe({
        next: (data) => {
          this.driverId = data.id;
          console.log(data.id);
          this.openPopup();
          console.log('Login successful', data);
        },
        error: (err) => {
          console.log('Error during the authentication process', err.error);
        },
      });
    }
  }
  openPopup() {
    this.visible = true;
  }
  closePopup() {
    this.visible = false;
    // this.displayStyle = 'none';
  }
}
