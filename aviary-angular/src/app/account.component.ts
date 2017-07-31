import {Component} from '@angular/core';
import {AngularFireAuth} from "angularfire2/auth";
import {Router} from "@angular/router";

@Component({
  selector: 'account',
  templateUrl: 'account.component.html',

})
export class AccountComponent {
  deleteConfirmation: Boolean;

  constructor(public auth: AngularFireAuth,
              private router: Router) {
  }

  deleteAccount() {
    this.auth.authState.subscribe(user => {
      user.delete().then(
        this.deletionSuccess,
        this.deletionError
      )
    })
  }

  private deletionSuccess = () => {
    this.router.navigate(['/']);
    console.log("Account deleted")
  };
  private deletionError = error => {
    console.log("Error occurred - account not deleted: " + JSON.stringify(error))
  };
}
