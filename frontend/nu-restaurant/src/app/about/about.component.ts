import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent {
  staff: string[] = ['Byte Bucătarul ', 'Script Ospatarul', 'Codrina Compilerescu', 'Bit Bogdan'];
  location: string = '12 Strada Pâinii Pufos, Gustopolis, 3456';
  aboutRestaurant: string = 'Savoarea Întâlnește Inovația: Descoperă Sfera Gastronomică a AC!';
}
