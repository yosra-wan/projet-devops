import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { AuthGuard } from 'src/app/guards/auth.guard';
import { AddEntrepriseComponent } from './components/add-entreprise/add-entreprise.component';
import { AddDriverComponent } from './components/add-driver/add-driver.component';
import { isAdminGuard } from 'src/app/guards/is-admin.guard';
import { isDriverGuard } from 'src/app/guards/is-driver.guard';
import { UserManagementComponent } from './components/user-management/user-management.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [AuthGuard] },
  { path: 'add-user', component: AddUserComponent },
  { path:'manage', component: UserManagementComponent},
  {
    path: 'add-entreprise',
    component: AddEntrepriseComponent,
    canActivate: [isAdminGuard],
  },
  {
    path: 'add-driver',
    component: AddDriverComponent,
    canActivate: [isDriverGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthentificationRoutingModule {}
