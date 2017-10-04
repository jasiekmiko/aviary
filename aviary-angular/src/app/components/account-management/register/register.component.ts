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
    let fbProfileDetails = {displayName: this.newUser.displayName(), photoURL: null};

    (this.auth.auth.createUserWithEmailAndPassword(this.newUser.email, this.newUser.password) as Promise<User>)
      .then(fbUser => {
        fbUser.updateProfile(fbProfileDetails);
        return this.saveNewUserInDb(this.newUser.toAviaryUser(fbUser.uid))
      })
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
              public firstName: string = "",
              public lastName: string = "",
              public password: string = "",
              public passwordRepeat: string = "",
              public dob: Date = new Date(),
              public gender: string = "") {
  }

  toAviaryUser(firebaseId: string): AviaryUser {
    return new AviaryUser(
      firebaseId,
      this.email,
      new Person(
        this.firstName,
        this.lastName,
        this.dob,
        this.gender
      ))
  }

  displayName() {
    return `${this.firstName} ${this.lastName}`;
  }
}

