import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

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
  ],
  imports: [
    BrowserModule,
    FormsModule,
    routing,
    HttpModule,
    AngularFireModule.initializeApp(firebaseConfig),
  ],
  providers: [
    AngularFireAuth,
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
