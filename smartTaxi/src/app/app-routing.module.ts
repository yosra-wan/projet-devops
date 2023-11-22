import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { PermissionGuard } from './guards/permission.guard';
import { CarManagementComponent } from './modules/car-management/components/car-management/car-management.component';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [PermissionGuard],
  },
  {
    path: 'carmanagement',
    component: CarManagementComponent,
    //canActivate: [PermissionGuard],
  },
  {
    path: 'not-authorized',
    component: NotAuthorizedComponent,
    //canActivate: [PermissionGuard],
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'auth',

    loadChildren: () =>
      import('./modules/authentification/authentification-routing.module').then(
        (m) => m.AuthentificationRoutingModule
      ),
  },
  {
    path: 'car',
    loadChildren: () =>
      import('./modules/car-management/car-management-routing.module').then(
        (m) => m.CarManagementModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
