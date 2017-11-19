import {Component, OnInit} from '@angular/core';
import {Tournament} from "../../models/Tournament";
import {HttpService} from "../../services/HttpService";

@Component({
  exportAs: "tournaments",
  selector: 'tournaments',
  templateUrl: 'tournaments.component.html',
  styleUrls: ['tournaments.component.scss']
})
export class TournamentsComponent implements OnInit {
  constructor(private http: HttpService) {
  }

  tournaments?: Tournament[];
  ngOnInit(): void {
    this.http.get("tournaments")
      .subscribe(resp => this.tournaments = resp.json());
  }

  joinTournament = (id: number) => {
    return this.http.post(`tournaments/${id}/players`, null)
      .subscribe(resp => console.log(resp))
  };
}
