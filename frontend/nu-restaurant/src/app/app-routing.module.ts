import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MeniuComponent } from './meniu/meniu.component';
import { AppComponent } from './app.component';
import { CategoriiComponent } from './categorii/categorii.component';
import { ProdusComponent } from './produs/produs.component';

const routes: Routes = [
  {path: '', component:AppComponent},
  {path:'meniu', component:MeniuComponent},
  {path:'categorii', component:CategoriiComponent},
  {path:'categorii/:name',component:ProdusComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
