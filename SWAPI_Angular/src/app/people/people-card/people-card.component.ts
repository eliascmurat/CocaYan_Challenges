import { Component, Input, OnInit } from '@angular/core';

import { People } from '../entities/people';

@Component({
  selector: 'app-people-card',
  templateUrl: './people-card.component.html',
  styleUrls: ['./people-card.component.css']
})
export class PeopleCardComponent implements OnInit {

  @Input() people!: People;

  constructor() { }

  ngOnInit() {
    console.log(this.people);
  }

}
