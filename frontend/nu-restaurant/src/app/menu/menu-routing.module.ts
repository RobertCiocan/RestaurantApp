import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ShowItemComponent } from './show-item/show-item.component';

const routes: Routes = [
  //{ path: '', component: ShowMenuComponent, pathMatch: 'full' },
  //localhost:4200/menu/item/id
  { path: 'item/:id', component: ShowItemComponent, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenuRoutingModule { }
