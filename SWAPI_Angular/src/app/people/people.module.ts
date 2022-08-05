import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ButtonsNavigationModule } from '../shared/buttons-navigation/buttons-navigation.module';
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
    ButtonsNavigationModule,
    LoadingModule
  ],
  declarations: [
    PeopleComponent
  ],
})
export class PeoplesModule { }
