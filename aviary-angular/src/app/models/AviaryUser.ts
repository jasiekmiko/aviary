export class Person {
  // noinspection JSUnusedGlobalSymbols
  constructor(public firstName: string,
              public lastName: string,
              public dob: Date, //Moment?
              public gender: string) {

  }
}

export class AviaryUser {
  // noinspection JSUnusedGlobalSymbols
  constructor(public firebaseId: string,
              public person: Person) {
  }
}
