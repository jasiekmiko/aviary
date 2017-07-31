import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from "./dashboard.component";
import {TournamentsComponent} from "./tournaments.component";
import {LoginComponent} from "./login.component";
import {RegisterComponent} from "./register.component";
import {AccountComponent} from "./account.component";

const appRoutes: Routes = [
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'tournaments',
        component: TournamentsComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'account',
        component: AccountComponent
    },
    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
];

export const routing = RouterModule.forRoot(appRoutes);