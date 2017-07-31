import {Component} from '@angular/core'
import {Router} from "@angular/router";
import {AngularFireAuth} from "angularfire2/auth";

@Component({
    selector: 'current-user',
    templateUrl: 'current-user.component.html',

})
export class CurrentUserComponent {
    constructor(
        public auth :AngularFireAuth,
        private router :Router) {
    }

    logout() {
        this.auth.auth.signOut();
        this.router.navigate(['/']);
    }
}
