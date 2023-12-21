import { Component } from '@angular/core';

@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.scss']
})
export class ReserveComponent {
  mese: number[] = Array.from({ length: 26 }, (_, i) => i + 1);

  masaSelectata: number | null = null;

  selecteazaMasa(masa: number) {
    this.masaSelectata = masa;
  }
  getMasaImage(masa: any): string {
    if (masa === this.masaSelectata) {
        return 'assets/table(2).png'; // Path to the image when selected
    } else {
        return 'assets/table.png'; // Default image
    }
}
}

