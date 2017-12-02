# Aviary
There are two components, aviary-api and aviary-angular with the front-end code.
The aviary-angular module is an angular website, hosted on a static-files bucket.
The aviary-api module is a [gae app](https://cloud.google.com/appengine/docs/standard/java/runtime-java8).

## Releasing
The two modules need to be released separately, see respective READMES for instructions

## Services used
Aviary uses 
- a [MySQL backed Cloud SQL](https://cloud.google.com/sql/docs/mysql/) database.
- [firebase](https://firebase.google.com/docs/auth/)-based user authentication. See [Authentication](Authentication)

## Development
In dev mode, the two components are run separately, to ease front-end development.
See the respective READMEs.

The project is maven-based, and it should be possible to run everything from command-line, using a combination of
- maven
- google cloud sdk
- angular-cli

Some documentation for this will be found in the READMEs. However, it's not been tried and tested fully.

The tested development environment is IntelliJ IDEA Ultimate, with corresponding plugins.

## Authentication
To provide a range of sign-in options for users, aviary utilises firebase.

### A user account creation flow:
1. Front-end talks directly to firebase to register a new user
1. Upon success, it tells the aviary backend to create a user entity, connected to the firebase id

### A user authentication flow:
1. Front-end talks directly to firebase to login a user, obtaining an auth token
1. It passes the firebase id and token to the backend with requests
1. The backed uses authenticates the token
