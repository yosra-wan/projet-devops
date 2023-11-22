import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const AuthGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  let token = localStorage.getItem('token');
  if (token) return router.navigate(['not-authorized']);
  return true;
};
