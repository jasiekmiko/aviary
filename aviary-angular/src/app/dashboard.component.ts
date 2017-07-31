import {Component, Inject} from '@angular/core';
import 'rxjs/add/operator/toPromise';
import {Response} from '@angular/http';
import {HttpService} from "./HttpService";

@Component({
    selector: 'dashboard',
    templateUrl: 'dashboard.component.html',
})
export class DashboardComponent {
    constructor(
        @Inject(HttpService) private http: HttpService
    ) { }

    private result :Response = null;

    test(){
        return this.http.get("auth")
            .subscribe(response => this.result = response,
              DashboardComponent.handleError);
    }

    private static handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}
