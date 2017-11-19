import {Tournament} from "./Tournament";
import {Person} from "./AviaryUser";

export class TournamentDetails {
  constructor(public players: Person[], public tournament: Tournament) {};
}
