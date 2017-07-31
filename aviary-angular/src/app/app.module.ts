import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HttpModule} from "@angular/http";
import {routing} from "./aviary.routing";
import {FormsModule} from "@angular/forms";
import {AccountComponent} from "./account.component";
import {RegisterComponent} from "./register.component";
import {LoginComponent} from "./login.component";
import {TournamentsComponent} from "./tournaments.component";
import {DashboardComponent} from "./dashboard.component";
import {CurrentUserComponent} from "./current-user.component";
import {HttpService} from "./HttpService";
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
    // {
    //   provide: HttpService,
    //   useFactory: function (backend: XHRBackend, options: RequestOptions, firebase: AngularFireAuth) {
    //     return new HttpService(backend, options, firebase);
    //   },
    //   deps: [XHRBackend, RequestOptions, AngularFireAuth]
    // },
    AngularFireAuth,
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
