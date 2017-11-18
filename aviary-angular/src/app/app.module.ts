import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MdInputModule, MdButtonModule, MdCardModule, MdDatepickerModule, MdNativeDateModule,
  MAT_DATE_LOCALE, MdToolbarModule
} from '@angular/material';

import { AppComponent } from './components/app/app.component';
import {HttpModule} from "@angular/http";
import {routing} from "./app.routing";
import {FormsModule} from "@angular/forms";
import {AccountComponent} from "./components/account-management/account/account.component";
import {RegisterComponent} from "./components/account-management/register/register.component";
import {LoginComponent} from "./components/account-management/login/login.component";
import {TournamentsComponent} from "./components/tournaments/tournaments.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {CurrentUserComponent} from "./components/account-management/current-user/current-user.component";
import {HttpService} from "./services/HttpService";
import {AngularFireModule} from "angularfire2";
import {AngularFireAuth} from "angularfire2/auth";
import { TournamentComponent } from './components/tournament/tournament.component';

const firebaseConfig = {
  apiKey: "AIzaSyB2L4Vmi5vur0F0Fp9i58rZldtkDZxY_dQ",
  authDomain: "aviary-130922.firebaseapp.com",
  databaseURL: "https://aviary-130922.firebaseio.com",
  storageBucket: "aviary-130922.appspot.com",
};

@NgModule({
  declarations: [
    AppComponent,
    CurrentUserComponent,
    DashboardComponent,
    TournamentsComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    TournamentComponent,
  ],
  imports: [
    BrowserModule,
    // The Material Components must come after the BrowserModule
    // Can be extracted into own module to declutter:
    // https://material.angular.io/guide/getting-started#step-3-import-the-component-modules
    MdInputModule,
    MdButtonModule,
    MdCardModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdToolbarModule,
    FormsModule,
    BrowserAnimationsModule,
    routing,
    HttpModule,
    AngularFireModule.initializeApp(firebaseConfig),
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
    AngularFireAuth,
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
