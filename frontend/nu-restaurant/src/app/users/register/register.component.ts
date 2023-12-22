import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UsersService } from '../users.service';
import { Router } from '@angular/router';

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
      this.usersService.registerUser(this.registerForm.value).subscribe(
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
