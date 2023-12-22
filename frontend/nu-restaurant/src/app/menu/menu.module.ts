import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MenuRoutingModule } from './menu-routing.module';
import { ShowItemComponent } from './show-item/show-item.component';

@NgModule({
  declarations: [ShowItemComponent],
  imports: [
    CommonModule,
    MenuRoutingModule
  ],
  exports:[ShowItemComponent]
})
export class MenuModule { }
