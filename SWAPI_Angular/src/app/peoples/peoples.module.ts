import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PeopleListModule } from './people-list/people-list.module';

import { PeoplesRoutingModule } from './peoples-routing.module';
import { PeoplesComponent } from './peoples.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    PeoplesRoutingModule,
    PeopleListModule
  ],
  declarations: [
    PeoplesComponent,
  ],
})
export class PeoplesModule { }
