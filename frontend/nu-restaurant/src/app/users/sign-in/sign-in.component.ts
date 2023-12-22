import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UsersService } from '../users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sing-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})

export class SignInComponent{
  signInForm: FormGroup = this.formBuilder.group({
    firstName: ['', Validators.required],
    password: ['', [Validators.required]],
  });

  // Example of a user service
  private readonly usersService: UsersService;
  private readonly router: Router;
  constructor(usersService: UsersService, router: Router, private formBuilder: FormBuilder) {
    this.usersService = usersService;
    this.router = router;
  }

  signIn() {
    if (this.signInForm.valid) {
      this.usersService.signInUser(this.signInForm.value).subscribe(
        (response) => {
          console.log('User signed in:', response);
        },
        (error) => {
          console.error('Sign in failed', error);
        }
      );
      this.router.navigate(['/menu']);
    }
  }
}
