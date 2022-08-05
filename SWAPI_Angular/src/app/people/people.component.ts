import { Component, OnInit } from '@angular/core';

import { People } from './people';
import { PeopleService } from './people.service';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  people!: People;
  initialPeopleId: number = 1;

  constructor(private peopleService: PeopleService) { }

  ngOnInit() {
    this.getPeopleById();
  }

  getPeopleById() {
    this.peopleService.getPeopleById(this.initialPeopleId)
      .then((response) => {
        this.people = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  }

}
