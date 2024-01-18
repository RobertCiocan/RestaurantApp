import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UsersService } from '../users.service';
import { Router } from '@angular/router';
import { ClientRequest } from '../models/Requests/client-request';
import { RegistrationRequest } from '../models/Requests/registration-request';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent{
  registerForm: FormGroup = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    address: ['', Validators.required],
    username: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
  });
  notificationMessage: string = '';
  

  // Example of a user service
  private readonly usersService: UsersService;
  private readonly router: Router;
  constructor(usersService: UsersService, router: Router, private formBuilder: FormBuilder) {
    this.usersService = usersService;
    this.router = router;
  }

  register() {
    if (this.registerForm.valid) {
      console.log("e valid");
      const registrationRequest: RegistrationRequest = {
        username: this.registerForm.value.username,
        password: this.registerForm.value.password,
        role: 'ROLE_USER',
      };
      const client_request:ClientRequest = {
        id: "",
        nume: this.registerForm.value.firstName,
        prenume: this.registerForm.value.lastName,
        email: this.registerForm.value.email,
        phone_number: this.registerForm.value.phoneNumber,
        address: this.registerForm.value.address,
        username: this.registerForm.value.username,
        password: this.registerForm.value.password
      };
      this.usersService.registerUser(registrationRequest).subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.error('Registration failed', error);
          this.notificationMessage = 'Înregistrarea a eșuat. Vă rugăm să încercați din nou.';
        }
      );

      this.usersService.createClient(client_request).subscribe(
        (response) => {
          console.log('Client created');
          this.notificationMessage = 'Utilizator creat cu succes!';
        },
        (error) => {
          console.error('Registration failed', error);
          this.notificationMessage = 'Înregistrarea a eșuat. Vă rugăm să încercați din nou.';
        }
      );
    }
  }
}
