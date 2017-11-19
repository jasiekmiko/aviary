import {Component, OnInit} from '@angular/core';
import {Tournament} from "../../models/Tournament";
import {HttpService} from "../../services/HttpService";

@Component({
  selector: 'tournaments',
  templateUrl: 'tournaments.component.html',
  styleUrls: ['tournaments.component.scss']
})
export class TournamentsComponent implements OnInit {
  constructor(private http: HttpService) {
  }

  tournaments?: Tournament[];
  ngOnInit(): void {
    this.http.get<Tournament[]>("tournaments")
      .subscribe(respBody => this.tournaments = respBody);
  }
}
