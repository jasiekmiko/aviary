# AviaryAngular

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.1.1.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|module`.

## Build
1. Run `ng build` to build the project into  the `dist/` directory.
   - This `dist/` should be deployed to one of the website-hosting buckets in Google storage under the appropriate name.
Use the `--env=prod` flag for a production build. Ideally we'd use the `--prod` flag which also turns AOT compilation
on, but this throws an error at the time of writing.
1. Run `gsutil.cmd rsync -d -r dist gs://aviary.bluehawkqs.eu` to publish it online

 TODO set up the website to be loaded over HTTPS using a [CDN](https://cloudplatform.googleblog.com/2015/09/push-google-cloud-origin-content-out-to-users.html)
Alternatively swap to [firebase hosting](https://firebase.google.com/docs/hosting/quickstart)   

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
