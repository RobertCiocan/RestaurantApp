import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MeniuComponent } from './meniu/meniu.component';
import { ProdusComponent } from './produs/produs.component';
import { CategoriiComponent } from './categorii/categorii.component';
import { ContactComponent } from './contact/contact.component';
import { HeaderComponent } from './header/header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// pentru serviciul simulat
import { HttpClientModule } from '@angular/common/http';

import { MenuModule } from './menu/menu.module';
import { UsersModule } from './users/users.module';

@NgModule({
  declarations: [
    AppComponent,
    MeniuComponent,
    ProdusComponent,
    CategoriiComponent,
    ContactComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    UsersModule,
    MenuModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
