import {Inject} from '@angular/core';
import {Headers, Http, RequestOptions, RequestOptionsArgs, Response, XHRBackend} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {AngularFireAuth} from "angularfire2/auth";
import {User} from "firebase/app";
import {environment} from "../../environments/environment";

export class HttpService extends Http {
  constructor(@Inject(XHRBackend) backend: XHRBackend,
              @Inject(RequestOptions) options: RequestOptions,
              @Inject(AngularFireAuth) private auth: AngularFireAuth) {
    super(backend, options);
  }

  get<T>(route: string, options: RequestOptionsArgs = {headers: new Headers()}): Observable<T> {
    return this.setAuthorizationHeader(options)
      .flatMap(options => super.get(HttpService.createApiUrl(route), options))
      .catch(this.catchAuthError)
      .map((resp: Response) => resp.json())
  }

  post(route: string, body: any, options: RequestOptionsArgs = {headers: new Headers()}): Observable<Response> {
    return this.setAuthorizationHeader(options)
      .flatMap(options => super.post(HttpService.createApiUrl(route), body, options))
      .catch(this.catchAuthError);
  }

  private static createApiUrl(route: string) {
    return environment.apiAddress + route;
  }

  private setAuthorizationHeader(options: RequestOptionsArgs) {
    return this.auth.authState.flatMap((user: User | null) => {
      if (user != null) {
        return Observable.fromPromise(user.getIdToken() as Promise<string>)
      }
      else return Observable.from([""])
    }).map((token: string) => {
      options.headers.set('Authorization', `Bearer ${token}`);
      options.headers.set('Content-Type', `application/json`);
      return options;
    });
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
