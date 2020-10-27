import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PoaListComponent} from './poa-list/poa-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/poa-list', pathMatch: 'full' },
  {
    path: 'poa-list',
    component: PoaListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
