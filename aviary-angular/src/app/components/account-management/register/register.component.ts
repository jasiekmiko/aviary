import {Component} from '@angular/core'
import {Router} from "@angular/router";
import {AngularFireAuth} from "angularfire2/auth";

@Component({
    selector: 'register-area',
    templateUrl: 'register.component.html',

})
export class RegisterComponent {
    constructor(
        private auth :AngularFireAuth,
        private router :Router) {
        auth.authState.subscribe(user => {
            if (user) {
                this.router.navigate(['/'])
            }
        });
    }
    error;
    newUser = {email: '', name: '', dob: Date(), password: '', passwordRepeat: ''};

    register() {
        this.error = null;

        this.auth.auth.createUserWithEmailAndPassword(this.newUser.email,this.newUser.password)
          .then(
            this.registrationSuccessful,
            this.registrationError
          )
    }

    registrationSuccessful = authState => this.router.navigate(['/login']); //TODO: send new user to aviary backend
    registrationError = error => this.error = error
}
