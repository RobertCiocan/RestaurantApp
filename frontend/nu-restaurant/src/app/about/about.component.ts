import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent {
  staff: string[] = ['Employee Name 1', 'Employee Name 2', 'Employee Name 3'];
  location: string = 'Restaurant Address, City, Country';
  aboutRestaurant: string = 'A few words about our restaurant...';
}
