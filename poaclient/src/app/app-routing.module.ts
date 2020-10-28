import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PoaListComponent} from './poa-list/poa-list.component';
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'login',
    component: LoginComponent
  },
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
