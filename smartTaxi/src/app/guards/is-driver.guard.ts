import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthentificationService } from '../modules/authentification/services/authentification.service';
import jwt_decode from 'jwt-decode';

export const isDriverGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  const authServ: AuthentificationService = inject(AuthentificationService);
  let token = localStorage.getItem('token');
  if (token) {
    try {
      const decodedToken: any = jwt_decode(token);
      const email = decodedToken.sub;
      authServ.isDriver(email).subscribe({
        next: (isDriver) => {
          if (isDriver) {
            router.navigate(['not-authorized']);
          }
        },
        error: (err) => {
          console.log(err);
        },
      });
    } catch (error) {
      console.error('Error decoding token:', error);
    }
  } else {
    console.error('Token not found in local storage.');
  }
  return true;
};
