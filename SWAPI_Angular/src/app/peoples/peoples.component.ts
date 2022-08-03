import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-peoples',
  templateUrl: './peoples.component.html',
  styleUrls: ['./peoples.component.css']
})
export class PeoplesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log('carregando peoples');
  }

}
