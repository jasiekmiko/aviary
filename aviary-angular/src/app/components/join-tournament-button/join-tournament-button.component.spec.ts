import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {JoinTournamentButtonComponent} from './join-tournament-button.component';

describe('JoinTournamentButtonComponent', () => {
  let component: JoinTournamentButtonComponent;
  let fixture: ComponentFixture<JoinTournamentButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinTournamentButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinTournamentButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
