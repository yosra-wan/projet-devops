import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthentificationService } from '../../services/authentification.service';

@Component({
  selector: 'app-add-entreprise',
  templateUrl: './add-entreprise.component.html',
  styleUrls: ['./add-entreprise.component.scss'],
})
export class AddEntrepriseComponent {
  EnterpriseForm: FormGroup;
  password: String = '';
  firstname: String = '';
  lastname: String = '';
  username: String = '';
  email: String = '';
  phone: String = '';
  matFiscal: String = '';
  address: String = '';
  isInvalid: any;
  isValid: any;
  isSubmitted = false;
  displayStyle = 'none';
  visible = false;

  constructor(
    private authServ: AuthentificationService,
    private router: Router
  ) {
    this.EnterpriseForm = new FormGroup({
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
      matriculeFiscale: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        this.noWhiteSpaceValidator,
      ]),
      address: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        this.trimValidator,
      ]),
    });
  }

  noWhiteSpaceValidator(ctrl: FormControl): { [s: string]: boolean } | null {
    if ((ctrl.value as string).indexOf(' ') >= 0) {
      return { cannotContainSpace: true };
    }

    return null;
  }

  trimValidator(ctrl: FormControl): { [s: string]: boolean } | null {
    if (ctrl.value && ctrl.value.trim() === '') {
      return { trim: true };
    }
    return null;
  }

  isFieldInvalid(fieldName: string) {
    const loginControl = this.EnterpriseForm.get(fieldName);
    this.isInvalid = loginControl?.invalid && loginControl.touched;
    return this.isInvalid;
  }

  isFieldValid(fieldName: string) {
    const loginControl = this.EnterpriseForm.get(fieldName);
    this.isValid = loginControl?.valid && loginControl.touched;
    return this.isValid;
  }

  signup() {
    this.isSubmitted = true;
    const user = this.EnterpriseForm.value;
    console.log('user', user);

    user.phone = '+216' + user.phone;

    if (this.EnterpriseForm.invalid) {
      alert('verifier les champs');
    } else {
      this.openPopup();
      this.authServ.addEntreprise(user).subscribe({
        next: (data) => {
          // localStorage.setItem('token', data['token']);
           this.router.navigate(['/auth/manage']);
        },
        error: (err) => {
          console.log('Error during the authentication process', err);
          // alert(err);
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
