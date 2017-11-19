import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../services/HttpService";
import {ActivatedRoute} from "@angular/router";
import {Person} from "../../models/AviaryUser";
import {Tournament} from "../../models/Tournament";
import {TournamentDetails} from "../../models/TournamentDetails";

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.scss']
})
export class TournamentComponent implements OnInit {
  constructor(private http: HttpService, private route: ActivatedRoute) {
  }

  private tournamentId: number;
  public tournament: Tournament;
  public players: Person[];
  joinSuccess = () => {
    // TODO get real data here from user object once it exists
    //this.players.push(new Person("New", "Person", "26/04/1993", "male"))
    this.loadData()
  };

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.tournamentId = params['id'];
      this.loadData();
    });

  }

  private loadData() {
    this.http.get<TournamentDetails>(`tournaments/${this.tournamentId}/players`)
      .subscribe(resp => {
        this.players = resp.players;
        this.tournament = resp.tournament;
      });
  }
}
