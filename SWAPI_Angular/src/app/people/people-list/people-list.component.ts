import { People } from './../people';
import { Component, OnInit } from '@angular/core';
import { PeopleService } from '../people.service';

@Component({
  selector: 'app-people-list',
  templateUrl: './people-list.component.html',
  styleUrls: ['./people-list.component.css']
})
export class PeopleListComponent implements OnInit {

  peopleList: People[] = [];

  constructor(
    private peopleService: PeopleService
  ) { }

  ngOnInit(): void {
    console.log('carregando people list');
    this.getAllPeoples();
  }

  getAllPeoples() {
    this.peopleService.getAllPeoples().then((peoples: any) => {
      this.peopleList = peoples.results;
    });
  }

}
