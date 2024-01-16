import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MeniuComponent } from './menu/meniu/meniu.component';
import { AppComponent } from './app.component';
import { ProdusComponent } from './menu/produs/produs.component';
import { ReserveComponent } from './reserve/reserve.component';

import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';

const routes: Routes = [
  {
    path:'contact',
    component:ContactComponent
  },
  {
    path:'about',
    component:AboutComponent
  },
  // {
  //   path:'sign-in',
  //   component:SignInComponent
  // },
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
    path:'categorii/:name',component:ProdusComponent
  },
  {
    path: 'rezervare',
    component: ReserveComponent,
    // component:ContactComponent
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
