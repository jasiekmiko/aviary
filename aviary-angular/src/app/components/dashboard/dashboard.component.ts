import {Component, Inject} from '@angular/core';
import 'rxjs/add/operator/toPromise';
import {HttpService} from "../../services/HttpService";

@Component({
  selector: 'dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.scss']
})
export class DashboardComponent {
  constructor(@Inject(HttpService) private http: HttpService) {
  }

  result = "";

  test() {
    return this.http.get<{userId: string}>("auth")
      .subscribe(
        response => this.result = response.userId,
        error => this.result = error.message);
  }
}
