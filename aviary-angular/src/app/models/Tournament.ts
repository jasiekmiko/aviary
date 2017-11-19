export class Tournament {
  constructor(public id: number = 0,
              public name: string,
              public description: string,
              public location: string,
              public startDate: Date,
              public endDate: Date,
              public registrationDeadline: Date,
              public size: number,
              public currentUserAttending: boolean) {
  }
}
