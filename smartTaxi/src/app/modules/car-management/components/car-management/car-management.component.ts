import { Component, OnInit } from '@angular/core';
import { PermissionHandler } from 'src/app/modules/shared/models/permission-handler';
import jwt_decode from 'jwt-decode';
import { CarService } from '../../services/car.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-management',
  templateUrl: './car-management.component.html',
  styleUrls: ['./car-management.component.scss'],
})
export class CarManagementComponent implements OnInit {
  voitures: any[] = []; 
  visible = false;

  constructor(private carService: CarService,private router: Router) {}

  carItem: PermissionHandler | undefined;
  ngOnInit(): void {
    this.fetchCars();
    let token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken: any = jwt_decode(token);
        console.log(decodedToken);

        this.carItem = decodedToken.CarManagement as PermissionHandler;
        console.log('homeItem', this.carItem);
      } catch (error) {
        console.error('Error decoding token:', error);
      }
    } else {
      console.error('Token not found in local storage.');
    }
  }
  
  fetchCars() {
    this.carService.getAllCar().subscribe({
      next: (data) => {
        this.voitures = data; 
        console.log(this.voitures);
        
      },
      error: (error) => {
        console.error('Une erreur s\'est produite lors de la récupération des voitures :', error);
      }
    });
  }
  openPopup() {
    this.visible = true;
  }
  closePopup() {
    this.visible = false;
   
  }

  navigateToMap() {
    this.router.navigate(['/car/map']);
  }
  
  
}
