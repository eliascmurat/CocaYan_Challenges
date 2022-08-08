import { Component, OnInit } from '@angular/core';

import { People } from './entities/people';
import { PeopleService } from './services/people.service';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  people!: People;
  initialPeopleId: number = 1;

  loading: boolean = false;
  notFound: boolean = false;

  constructor(private peopleService: PeopleService) { }

  ngOnInit() {
    this.getPeopleById(this.initialPeopleId);
  }

  getPeopleById(peopleId: number) {
    this.loading = true;

    this.peopleService.getPeopleById(peopleId)
    .then((response) => {
      this.people = response.data;
      this.notFound = false;
    })
    .catch((error) => {
      this.notFound = true;
    })
    .finally(() => {
      this.loading = false;
    });
  }

  prevPeople() {
    this.getPeopleById(--this.initialPeopleId);
  }

  nextPeople() {
    this.getPeopleById(++this.initialPeopleId);
  }

}
