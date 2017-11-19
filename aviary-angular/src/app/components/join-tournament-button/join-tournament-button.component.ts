import {Component, Input} from '@angular/core';
import {Tournament} from "../../models/Tournament";
import {HttpService} from "../../services/HttpService";

@Component({
  selector: 'join-tournament-button',
  templateUrl: './join-tournament-button.component.html',
  styleUrls: ['./join-tournament-button.component.scss']
})
export class JoinTournamentButtonComponent {
  constructor(private http: HttpService) {  }

  @Input() tournament: Tournament;
  @Input() onSuccess?: () => void;

  joinTournament = (id: number) => {
    return this.http.post(`tournaments/${id}/players`, null)
      .subscribe(() => {
        this.tournament.currentUserAttending = true;
        if (this.onSuccess != null) {
          this.onSuccess()
        }
      })
  };
}
