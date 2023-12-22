import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MeniuComponent } from './meniu/meniu.component';
import { AppComponent } from './app.component';
import { CategoriiComponent } from './categorii/categorii.component';
import { ProdusComponent } from './produs/produs.component';


import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
  {
    path:'contact',
    component:ContactComponent,
  },
  {
    path: 'users',
    loadChildren: () => import('./users/users.module').then(mod => mod.UsersModule)
  },
  { 
    path: 'menu',
    loadChildren: () => import('./menu/menu.module').then(mod => mod.MenuModule)
  },
  {
    path:'meniu', component:MeniuComponent
  },
  {
    path:'categorii', component:CategoriiComponent
  },
  {
    path:'categorii/:name',component:ProdusComponent
  },
  {
    path: "**",
    redirectTo: ""
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
