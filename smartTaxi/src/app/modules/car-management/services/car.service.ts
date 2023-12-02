import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CarService {
  private ip = '34.200.127.95';
  constructor(private http: HttpClient) {}

  getAllCar(): Observable<any> {
    return this.http.get( `http://${this.ip}:8082/api/car/all-cars`);
  }
  ajouterVoiture(nouvelleVoiture: any): Observable<any> {
    
    return this.http.post( `http://${this.ip}:8082/api/car/add`, nouvelleVoiture);
  }
  assignCarToDriver(credentials: {
    id: any;
    currentUserId: any;
  }): Observable<any> {
    return this.http.post(
      `http://${this.ip}:8082/api/car/history/create`,
      credentials
    );
  }
}
