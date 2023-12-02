import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthentificationService {
  constructor(private http: HttpClient) {}
  private ip = '34.200.127.95';
  login(credentials: { login: string; password: string }): Observable<any> {
    return this.http.post(
      `http://${this.ip}:8081/api/auth/v1/login`,
      credentials
    );
  }

  register(user: any): Observable<any> {
    return this.http.post(`http://${this.ip}:8081/api/auth/v1/register`, user);
  }

  addEntreprise(entreprise: any): Observable<any> {
    return this.http.post(
      `http://${this.ip}:8081/api/auth/v1/add-enterprise`,
      entreprise
    );
  }

  addDriver(driver: any): Observable<any> {
    return this.http.post(
      `http://${this.ip}:8081/api/auth/v1/add-driver`,
      driver
    );
  }

  isAdmin(email: string): Observable<boolean> {
    return this.http.get<boolean>(
      `http://${this.ip}:8081/api/auth/v1/is-admin?email=${email}`
    );
  }

  isDriver(email: string): Observable<boolean> {
    return this.http.get<boolean>(
      `http://${this.ip}:8081/api/auth/v1/is-driver?email=${email}`
    );
  }

  isEntreprise(email: string): Observable<boolean> {
    return this.http.get<boolean>(
      `http://${this.ip}:8081/api/auth/v1/is-entreprise?email=${email}`
    );
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>( `http://${this.ip}:8081/api/auth/v1/getAllUsers`);
  }
}
