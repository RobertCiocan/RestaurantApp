import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactComponent } from './contact/contact.component';
import { ReserveComponent } from './reserve/reserve.component';

const routes: Routes = [
  {
    path:'contact',
    component:ContactComponent
  },
  {
    path: 'rezervare',
    component: ReserveComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
