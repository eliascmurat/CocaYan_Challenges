import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { PeopleListComponent } from './people-list.component';
import { PeopleCardComponent } from './people-card/people-card.component';

@NgModule({
  imports: [
    CommonModule,
  ],
  declarations: [
    PeopleListComponent,
    PeopleCardComponent
  ],
  exports: [
    PeopleListComponent
  ]
})
export class PeopleListModule { }
