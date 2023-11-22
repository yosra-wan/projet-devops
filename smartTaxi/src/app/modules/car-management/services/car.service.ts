import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CarService {
  constructor(private http: HttpClient) {}

  getAllCar(): Observable<any> {
    return this.http.get('http://localhost:8082/api/car/all-cars');
  }
  ajouterVoiture(nouvelleVoiture: any): Observable<any> {
    
    return this.http.post('http://localhost:8082/api/car/add', nouvelleVoiture);
  }
  assignCarToDriver(credentials: {
    id: any;
    currentUserId: any;
  }): Observable<any> {
    return this.http.post(
      'http://localhost:8082/api/car/history/create',
      credentials
    );
  }
}
