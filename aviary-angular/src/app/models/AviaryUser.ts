export class Person {
  // noinspection JSUnusedGlobalSymbols
  constructor(public firstName: string,
              public lastName: string,
              public dob: Date,
              public gender: string) {

  }
}

export class AviaryUser {
  // noinspection JSUnusedGlobalSymbols
  constructor(public firebaseId: string,
              public email: string,
              public person: Person) {
  }
}
