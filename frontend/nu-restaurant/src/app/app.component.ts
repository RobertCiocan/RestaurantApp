import { Component, isStandalone } from '@angular/core';
import { MeniuComponent } from './meniu/meniu.component';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})

export class AppComponent {
  title = 'nu-restaurant';
}
