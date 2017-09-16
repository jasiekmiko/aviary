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
            .subscribe(
              response => this.result = response.text(),
              error => this.result = error.message);
    }
}
