import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-buttons-navigation',
  templateUrl: './buttons-navigation.component.html',
  styleUrls: ['./buttons-navigation.component.css']
})
export class ButtonsNavigationComponent implements OnInit {

  @Input() index: number = 1;

  constructor() { }

  ngOnInit() { }

  previousPeople() {
    console.log(--this.index);
  }

  nextPeople() {
    console.log(++this.index)
  }

}
