import { Component, OnInit } from '@angular/core';
import {HttpService} from "../../services/HttpService";
import {ActivatedRoute} from "@angular/router";
import {Person} from "../../models/AviaryUser";

@Component({
  selector: 'app-tournament',
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.scss']
})
export class TournamentComponent implements OnInit {
  constructor(private http: HttpService, private route: ActivatedRoute) {
  }

  private tournamentId: number;
  public players: Person[];

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.tournamentId = params['id'];
      this.http.get(`tournaments/${this.tournamentId}/players`)
        .subscribe(resp => this.players = resp.json());
    });

  }

}
