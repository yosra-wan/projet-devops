import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthentificationRoutingModule } from './authentification-routing.module';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddUserComponent } from './components/add-user/add-user.component';
import { AddEntrepriseComponent } from './components/add-entreprise/add-entreprise.component';
import { AddDriverComponent } from './components/add-driver/add-driver.component';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { CarManagementModule } from '../car-management/car-management.module';
@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    AddUserComponent,
    AddEntrepriseComponent,
    AddDriverComponent,
  ],
  imports: [
    CommonModule,
    AuthentificationRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    ButtonModule,
    DialogModule,
    DynamicDialogModule,
    CarManagementModule,
  ],
})
export class AuthentificationModule {}
