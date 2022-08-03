import { Component, Input, OnInit } from '@angular/core';
import { People } from '../../people';

@Component({
  selector: 'app-people-card',
  templateUrl: './people-card.component.html',
  styleUrls: ['./people-card.component.css']
})
export class PeopleCardComponent implements OnInit {

  @Input() people!: People;

  constructor() { }

  ngOnInit(): void {
    console.log('carregando people card');
    console.log('dados do people:');
    console.log(this.people);
  }

}
