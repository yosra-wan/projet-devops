import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/guards/auth.guard';
import { CarManagementComponent } from './components/car-management/car-management.component';
import { AddCarComponent } from './components/add-car/add-car.component';
import { AddCarHistoryComponent } from './components/add-car-history/add-car-history.component';
import { MapCarComponent } from './components/map-car/map-car.component';

const routes: Routes = [
  { path: '', component: CarManagementComponent },
  { path: 'add', component: AddCarComponent },
  { path: 'map', component: MapCarComponent },
  { path: 'history/add', component: AddCarHistoryComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CarManagementModule {}
