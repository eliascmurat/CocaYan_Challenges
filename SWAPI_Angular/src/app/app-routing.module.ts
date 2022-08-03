import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'people'
  },
  {
    path: 'people',
    loadChildren: () => import('./people/people.module').then(m => m.PeoplesModule)
  },
  {
    path: '**',
    redirectTo: 'people'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
