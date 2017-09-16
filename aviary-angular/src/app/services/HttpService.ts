import {Inject} from '@angular/core';
import {
  Http,
  XHRBackend,
  RequestOptions,
  RequestOptionsArgs,
  Response,
  Headers
} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {AngularFireAuth} from "angularfire2/auth";

export class HttpService extends Http {
  constructor(@Inject(XHRBackend) backend: XHRBackend,
              @Inject(RequestOptions) options: RequestOptions,
              @Inject(AngularFireAuth) private auth: AngularFireAuth) {
    super(backend, options);
  }

  get(route: string, options?: RequestOptionsArgs): Observable<Response> {
    const url = "http://localhost:8880/" + route;
    return this.auth
      .authState.flatMap(user => {
        if (user == null) {
          throw new Error("User is not logged in")
        }
        return Observable.fromPromise(user.getToken() as Promise<string>)
          .flatMap(token => {
            if (!options) {
              options = {headers: new Headers()};
            }
            options.headers.set('Authorization', `Bearer ${token}`);
            let response = super.get(url, options);
            response.catch(this.catchAuthError);
            return response;
          })
      })

  }

  private catchAuthError = (res: Response) => {
    console.log(res);
    if (res.status === 401 || res.status === 403) {
      // if not authenticated
      console.log(res);
    }
    return Observable.throw(res);
  };

}
