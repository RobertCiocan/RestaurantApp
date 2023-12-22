import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
    path: "**",
    redirectTo: ""
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
