import { People } from './../people';
import { Component, OnInit } from '@angular/core';
import { PeopleService } from '../people.service';

@Component({
  selector: 'app-people-list',
  templateUrl: './people-list.component.html',
  styleUrls: ['./people-list.component.css']
})
export class PeopleListComponent implements OnInit {

  peoples: People[] = [];

  constructor(private peopleService: PeopleService) { }

  ngOnInit(): void {
    this.getAllPeople();
  }

  getAllPeople() {
    this.peopleService.getAllPeoples()
      .then((response) => {
        this.peoples = response.data.results;
      })
      .catch((error) => {
        console.log(error);
      });
  }
}
