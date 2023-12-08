import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MeniuComponent } from './meniu/meniu.component';
import { ProdusComponent } from './produs/produs.component';
import { CategoriiComponent } from './categorii/categorii.component';

@NgModule({
  declarations: [
    AppComponent,
    MeniuComponent,
    ProdusComponent,
    CategoriiComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
