import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UsersService } from '../users.service';
import { Router } from '@angular/router';
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

  // Example of a user service
  private readonly usersService: UsersService;
  private readonly router: Router;
  constructor(usersService: UsersService, router: Router, private formBuilder: FormBuilder) {
    this.usersService = usersService;
    this.router = router;
  }

  register() {
    if (this.registerForm.valid) {
      const registrationRequest: RegistrationRequest = {
        username: this.registerForm.value.username,
        password: this.registerForm.value.password,
        role: "ROLE_USER"
      };

      this.usersService.registerUser(registrationRequest).subscribe(
        (response) => {
          console.log('User registered:', response);
        },
        (error) => {
          console.error('Registration failed', error);
        }
      );
      this.router.navigate(['/register']);
    }
  }
}
