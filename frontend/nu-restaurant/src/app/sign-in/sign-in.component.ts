import { Component } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {
  formGroup: FormGroup = new FormGroup({});
  email: FormControl = new FormControl('',[Validators.required,Validators.email]);
  password: FormControl = new FormControl('',[Validators.required]);

  ngOnInit(){
    this.formGroup = new FormGroup({
      email: this.email,
      password: this.password
    });
  }

  submitForm(){
    console.log(this.formGroup.controls['email'].value)
    console.log(this.formGroup.controls['password'].value)
  }
}
