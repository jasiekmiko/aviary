import {Component, Inject} from '@angular/core';
import 'rxjs/add/operator/toPromise';
import {HttpService} from "../../services/HttpService";

@Component({
    selector: 'dashboard',
    templateUrl: 'dashboard.component.html',
})
export class DashboardComponent {
    constructor(
        @Inject(HttpService) private http: HttpService
    ) { }

    private result = "";

    test(){
        return this.http.get("auth")
            .subscribe(response => this.result = response.text(),
              DashboardComponent.handleError);
    }

    private static handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}
