import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PeopleListModule } from './people-list/people-list.module';

import { PeoplesRoutingModule } from './people-routing.module';
import { PeopleComponent } from './people.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    PeoplesRoutingModule,
    PeopleListModule
  ],
  declarations: [
    PeopleComponent,
  ],
})
export class PeoplesModule { }
