import { Component } from '@angular/core';
import { ReserveService } from './rezervare/reserve.service';
import { Reserve } from './models/reserve.model';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.scss']
})
export class ReserveComponent {

  createForm: FormGroup = this.formBuilder.group({
    masa: ['', Validators.required],
    name:['', [Validators.required, Validators.maxLength(15)]],
    phone:['', [Validators.required, Validators.maxLength(10)]],
    data:['', Validators.required],
    time:['', Validators.required],
    time_end:['', Validators.required],
    guests:['', Validators.required],
    specialRequests:['', [Validators.required, Validators.maxLength(250)]]
  })

  private readonly reserveService: ReserveService;
  private readonly router: Router;
  detaliiRezervare: Reserve[] | null = null;
  constructor(reserveService: ReserveService, router: Router, private formBuilder: FormBuilder) {
    this.reserveService = reserveService;
    this.router = router;
  }
  
  create(){
    if(this.createForm.valid){
      const reserveReq: Reserve={
        masa:<string> this.createForm.value.masa,
        name:this.createForm.value.name,
        phone:this.createForm.value.phone,
        data: this.createForm.value.data,
        time: this.createForm.value.time + ":00",  
        time_end: this.createForm.value.time_end + ":00",
        guests:this.createForm.value.guests,
        specialRequests:this.createForm.value.specialRequests,
      };
      this.reserveService.createReserve(reserveReq).subscribe(
        (response)=>{
          console.log("Rezervare efectuata cu succes");
        },
        (error) => {
        if (error.status == 409) {
          alert("Data sau ora nu este buna. Alegeți alt interval.");
        } else {
          alert("Nu s-a efectuat rezervarea!");
        }
        }
      );
      // this.router.navigate(['/rezervare']);
    }
    else{
      console.log(this.createForm.value);
    }
  };

  afiseazaDetaliiMasa(masa: number) {
  this.reserveService.getReserveById(masa.toString()).subscribe(
    (rezervare) => {
      this.detaliiRezervare = rezervare || null;
    },
    (error) => {
      console.error('Eroare la obținerea detaliilor rezervării:', error);
    }
  );
};

  
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
formatareData(data:string): string {
    const dataFormatata = new Date(data);
    const numeLuna = dataFormatata.toLocaleDateString('ro-RO', { month: 'long' });
    const an = dataFormatata.getFullYear();
    const zi = dataFormatata.getDate();

    return `${an} ${zi} ${numeLuna}`;
  }
}