import { Component, OnInit } from '@angular/core';
import { AuthentificationService } from '../../services/authentification.service';
import { Observable, firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.scss']
})
export class UserManagementComponent implements OnInit {
  admins: any[] = [];
  chauffeurs: any[] = [];
  entreprises: any[] = [];
  visible: boolean = false; // Pour afficher ou masquer la boîte de dialogue
  userId: number=0;

  constructor(private authService: AuthentificationService) {}

  ngOnInit() {
    this.getAllUsers();
  }
  isAdmin(email: string): Observable<boolean> {
    return this.authService.isAdmin(email);
  }
  
  isDriver(email: string): Observable<boolean> {
    return this.authService.isDriver(email);
  }
  
  isEntreprise(email: string): Observable<boolean> {
    return this.authService.isEntreprise(email);
  }
  
  getAllUsers() {
    this.authService.getAllUsers().subscribe({
      next: async (users) => {
        if (users) {
          for (const user of users) {
            console.log(user);
            
            this.isDriver(user.email).subscribe({
              next: (isUserDriver) => {
                if (isUserDriver) {
                  this.chauffeurs.push(user);
                } 
              },
              error: (error) => {
                console.error('Error checking if user is a driver:', error);
              },
            });

            this.isAdmin(user.email).subscribe({
              next: (isUserAdmin) => {
                if (isUserAdmin) {
                  this.admins.push(user);
                } 
              },
              error: (error) => {
                console.error('Error checking if user is a driver:', error);
              },
            });

            this.isEntreprise(user.email).subscribe({
              next: (isUserEntreprise) => {
                if (isUserEntreprise) {
                  this.entreprises.push(user);
                } 
              },
              error: (error) => {
                console.error('Error checking if user is a driver:', error);
              },
            });

            

          }
        }
      },
      error: (error) => {
        console.error('Error fetching users:', error);
      },
    });
  }
  
  
  

  gererPermissions(user: any): void {
    // Logique pour gérer les permissions de l'utilisateur
    // Vous pouvez implémenter la logique de gestion des permissions ici
    console.log('Gérer les permissions de l\'utilisateur :', user);
 console.log(this.admins);
 
  }

  assignerChauffeur(chauffeur: any): void {
    // Logique pour assigner un chauffeur
    // Vous pouvez implémenter la logique d'assignation du chauffeur ici
   this.visible =true
    this.userId = chauffeur.id;
    console.log('Assigner le chauffeur :', chauffeur);
  }

  assignerEntreprise(entreprise: any): void {
    // Logique pour assigner une entreprise
    // Vous pouvez implémenter la logique d'assignation de l'entreprise ici
    this.userId = entreprise.id
    console.log('Assigner l\'entreprise :', entreprise);
  }
}
