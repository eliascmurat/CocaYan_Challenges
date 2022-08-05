import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PeopleCardModule } from './people-card/people-card.module';
import { PeoplesRoutingModule } from './people-routing.module';
import { PeopleComponent } from './people.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    PeoplesRoutingModule,
    PeopleCardModule
  ],
  declarations: [
    PeopleComponent
  ],
})
export class PeoplesModule { }
