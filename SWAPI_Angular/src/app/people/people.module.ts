import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LoadingModule } from '../shared/loading/loading.module';
import { PeopleCardModule } from './people-card/people-card.module';
import { PeoplesRoutingModule } from './people-routing.module';
import { PeopleComponent } from './people.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    PeoplesRoutingModule,
    PeopleCardModule,
    LoadingModule
  ],
  declarations: [
    PeopleComponent
  ],
})
export class PeoplesModule { }
