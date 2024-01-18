
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReserveComponent } from '../reserve.component';

const routes: Routes = [
  { path: '/rezervare', component: ReserveComponent, pathMatch: 'full' },
 // { path: 'rezervare/:id', component: ReserveComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReserveRoutingModule { }