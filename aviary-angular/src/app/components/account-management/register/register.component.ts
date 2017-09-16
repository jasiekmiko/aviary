import {Component} from '@angular/core'
import {Router} from "@angular/router";
import {AngularFireAuth} from "angularfire2/auth";
import {User} from "firebase/app";
import {HttpService} from "../../../services/HttpService";

@Component({
  selector: 'register-area',
  templateUrl: 'register.component.html',
})
export class RegisterComponent {
  constructor(private auth: AngularFireAuth,
              private router: Router,
              private http: HttpService) {
    auth.authState.subscribe(user => {
      if (user) {
        this.router.navigate(['/'])
      }
    });
  }

  error;
  newUser = new NewUser();

  register() {
    this.error = null;

    (this.auth.auth.createUserWithEmailAndPassword(this.newUser.email, this.newUser.password) as Promise<User>)
      .then(this.saveNewUserInDb(this.newUser))
      .then(this.navigateToLogin)
      .catch(this.showError)
  }
  private saveNewUserInDb(userData: NewUser) {
    const body = {
      email: userData.email,
      name: userData.name,
    };
  return () => this.http.post("users", body).toPromise();
}
  private navigateToLogin = authState => this.router.navigate(['/login']);
  private showError = error => this.error = error
}

class NewUser {
  constructor(public email: string = "",
              public name: string = "",
              public password: string = "",
              public passwordRepeat: string = "") {
  }
}

