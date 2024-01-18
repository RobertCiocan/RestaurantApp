import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UsersService } from '../users/users.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  constructor(private userService: UsersService, private router: Router) {}

  isUserLoggedIn(): boolean {
    const jwt = sessionStorage.getItem('jwt');
    return jwt !== null && jwt !== undefined;
  }

  logout(): void {
    // Call the logoutUser method from the UsersService
    this.userService.logoutUser(sessionStorage.getItem('jwt')).subscribe(
      () => {
        console.log('Logout successful');

        this.router.navigate(['/home']);
      },
      (error) => {
        console.error('Error logging out', error);
      }
    );
  }
}
