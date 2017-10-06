import {Component} from '@angular/core'
import {Router} from "@angular/router";
import {AngularFireAuth} from "angularfire2/auth";
import {auth} from "firebase/app";

@Component({
    selector: 'login-area',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.scss']

})
export class LoginComponent {
    public error;
    public loginCredentials = {email: '', password: ''};

    constructor(
        private auth :AngularFireAuth,
        private router :Router) {

        auth.authState.subscribe(user => {
            if (user) {
                this.router.navigate(['/'])
            }
        });
    }

    googleLogin() {
        this.auth.auth.signInWithPopup(new auth.GoogleAuthProvider())
          .then(
            this.loginSuccessful,
            this.loginError
        );
    }

    facebookLogin() {
        this.auth.auth.signInWithPopup(new auth.FacebookAuthProvider())
        .then(
            this.loginSuccessful,
            this.loginError
        )
    }

    login() {
        this.error = null;
        this.auth.auth.signInWithEmailAndPassword(this.loginCredentials.email, this.loginCredentials.password).then(
            this.loginSuccessful,
            this.loginError
        )
    }

    loginSuccessful = () => {
        this.router.navigate(['/'])
    };

    loginError = error => {
        this.error = error
    }
}
