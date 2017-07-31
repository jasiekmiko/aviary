import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {TournamentsComponent} from "./components/tournaments/tournaments.component";
import {LoginComponent} from "./components/account-management/login/login.component";
import {RegisterComponent} from "./components/account-management/register/register.component";
import {AccountComponent} from "./components/account-management/account/account.component";

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
