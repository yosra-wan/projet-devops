import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthentificationModule } from './modules/authentification/authentification.module';
import { CarManagementComponent } from './modules/car-management/components/car-management/car-management.component';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';
import { STPermissionDirective } from './modules/shared/shared-directives/stpermission.directive';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { TableModule } from 'primeng/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CarManagementModule } from './modules/car-management/car-management.module';
import { MapCarComponent } from './modules/car-management/components/map-car/map-car.component';
import { AddCarComponent } from './modules/car-management/components/add-car/add-car.component';
import { UserManagementComponent } from './modules/authentification/components/user-management/user-management.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CarManagementComponent,
    NotAuthorizedComponent,
    STPermissionDirective,
    MapCarComponent,
    AddCarComponent,
    UserManagementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    FormsModule,
    AuthentificationModule,
    ButtonModule,
    TableModule,
    DialogModule,
    BrowserAnimationsModule,
    CarManagementModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
