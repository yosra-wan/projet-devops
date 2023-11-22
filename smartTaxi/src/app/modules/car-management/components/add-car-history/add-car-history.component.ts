import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { Dialog } from 'primeng/dialog';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-add-car-history',
  templateUrl: './add-car-history.component.html',
  styleUrls: ['./add-car-history.component.scss'],
})
export class AddCarHistoryComponent implements OnInit {
  historyForm: FormGroup;
  cars: any[] = [];

  @Input() receivedDriverId: number | undefined;
  @Input() visibileDialog: boolean | undefined;
  @ViewChild('carHistoryDialog') carHistoryDialog: Dialog | undefined;

  constructor(private carServ: CarService, private route: Router) {
    this.historyForm = new FormGroup({
      carId: new FormControl('', Validators.required),
    });
  }
  ngOnInit(): void {
    this.carServ.getAllCar().subscribe({
      next: (data) => {
        this.cars = data;
        console.log(data);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  submit() {
    console.log('receivedDriverId', this.receivedDriverId);
    console.log(this.visibileDialog);

    const formValue = this.historyForm.value;
    const carId = formValue.carId;
    console.log('carId.id', carId);

    const credentials = {
      id: carId,
      currentUserId: this.receivedDriverId,
    };

    this.carServ.assignCarToDriver(credentials).subscribe({
      next: (data) => {
        console.log(data);
        alert('car assigned saccessfully');
        this.route.navigate(['/home']);
      },
      error: (err) => {
        alert(err.message);
        console.log('Error', err);
      },
    });
  }
}
