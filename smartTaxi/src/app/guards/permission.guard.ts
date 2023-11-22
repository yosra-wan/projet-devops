import { CanActivateFn, Router } from '@angular/router';
import jwt_decode from 'jwt-decode';
import { inject } from '@angular/core';
import { PermissionHandler } from '../modules/shared/models/permission-handler';

export const PermissionGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  let token = localStorage.getItem('token');
  if (token) {
    try {
      const decodedToken: any = jwt_decode(token);
      console.log(decodedToken);

      const subItem = decodedToken.Home as PermissionHandler;
      console.log(subItem);

      if (!subItem.canDisplay) {
        router.navigate(['not-authorized']);
      }
    } catch (error) {
      console.error('Error decoding token:', error);
    }
  } else {
    console.error('Token not found in local storage.');
  }
  return true;
};
