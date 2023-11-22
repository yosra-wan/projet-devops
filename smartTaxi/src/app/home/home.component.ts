import { Component, OnInit } from '@angular/core';
import jwt_decode from 'jwt-decode';
import { PermissionHandler } from '../modules/shared/models/permission-handler';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  homeItem: PermissionHandler | undefined;

  ngOnInit(): void {
    let token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken: any = jwt_decode(token);
        console.log(decodedToken);

        this.homeItem = decodedToken.Home as PermissionHandler;
        console.log('homeItem', this.homeItem);
      } catch (error) {
        console.error('Error decoding token:', error);
      }
    } else {
      console.error('Token not found in local storage.');
    }
  }
}

// TODO deplacer dans models dans shared
// export interface PermissionHandler {
//   canDelete: boolean;
//   canAdd: boolean;
//   canDisplay: boolean;
// }
