import { Component } from '@angular/core';
import { CarService } from '../../services/car.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.scss'],
})
export class AddCarComponent {
  nouvelleVoiture: any = {};
  ajoutSucces: boolean = false;
  carForm:FormGroup
  
  constructor(private carService: CarService) {
    this.carForm = new FormGroup({
      make: new FormControl('', [Validators.required]),
      model: new FormControl('', [Validators.required]),
      manufacturingDate: new FormControl('', [Validators.required]),
      registrationNumber: new FormControl('', [Validators.required]),
      seatsNumber: new FormControl('', [Validators.required]),
    });
  }
  
  ajouterVoiture() {
    if (this.carForm.valid) {
      const formData = this.carForm.value;
      this.carService.ajouterVoiture(formData).subscribe({
        next: (response) => {
          console.log('Réponse du serveur :', response);
  
          if (response && response instanceof HttpResponse) {
            // Vérifiez que la réponse est un JSON valide avant de le traiter
            try {
              const responseData = response.body;
              console.log('Données de réponse JSON :', responseData);
              this.ajoutSucces = true;
              this.carForm.reset(); // Réinitialise le formulaire après l'ajout réussi
            } catch (error) {
              console.error('Erreur lors de la conversion de la réponse JSON :', error);
            }
          }
        },
        error: (error) => {
          console.error('Erreur lors de l\'ajout de la voiture :', error);
        }
      });
    }
  }
  
  
}
