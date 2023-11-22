import { Inject, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { PermissionHandler } from '../modules/shared/models/permission-handler';
import jwt_decode from 'jwt-decode';
import { AuthentificationService } from '../modules/authentification/services/authentification.service';

export const isAdminGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  const authServ: AuthentificationService = inject(AuthentificationService);
  let token = localStorage.getItem('token');
  if (token) {
    try {
      const decodedToken: any = jwt_decode(token);
      const email = decodedToken.sub;

      authServ.isAdmin(email).subscribe({
        next: (isAdmin) => {
          console.log(isAdmin);

          if (!isAdmin) {
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
