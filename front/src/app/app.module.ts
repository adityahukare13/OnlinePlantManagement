import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PlantListComponent } from './Plant/plant-list/plant-list.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {  HttpClientModule} from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CoupanComponent } from './Coupan/coupan/coupan.component';

@NgModule({
  declarations: [
    AppComponent,
    PlantListComponent,
    NavBarComponent,
    CoupanComponent
  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
     FormsModule,
    HttpClientModule, CommonModule
    // ,
    // InMemoryWebApiModule.forRoot(TestData)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
