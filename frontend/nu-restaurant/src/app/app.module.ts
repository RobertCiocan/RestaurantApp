import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MeniuComponent } from './menu/meniu/meniu.component';
import { ProdusComponent } from './menu/produs/produs.component';
import { ContactComponent } from './contact/contact.component';
import { HeaderComponent } from './header/header.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// pentru serviciul simulat
import { HttpClientModule } from '@angular/common/http';

import { MenuModule } from './menu/menu.module';
import { UsersModule } from './users/users.module';
import { ReserveComponent } from './reserve/reserve.component';
import { AboutComponent } from './about/about.component';
@NgModule({
  declarations: [
    AppComponent,
    MeniuComponent,
    ProdusComponent,
    ContactComponent,
    HeaderComponent,
    ReserveComponent,
    AboutComponent,
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
