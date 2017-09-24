import {Component} from '@angular/core'
import {Router} from "@angular/router";
import {AngularFireAuth} from "angularfire2/auth";
import {User} from "firebase/app";
import {HttpService} from "../../../services/HttpService";
import {AviaryUser, Person} from "../../../models/AviaryUser";

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
      .then(fbUser => this.saveNewUserInDb(this.newUser.toAviaryUser(fbUser.uid)))
      .then(this.navigateToLogin)
      .catch(this.showError)
  }

  private saveNewUserInDb(userData: AviaryUser) {
    return this.http.post("users", userData).toPromise();
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

  toAviaryUser(firebaseId: string): AviaryUser {
    //TODO Get better data. Add email
    return new AviaryUser(firebaseId, new Person(
      this.name,
      "",
      new Date("2013-02-04"),
      "person"
    ))
  }
}

