import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AuthComponent} from "./auth.component";
import {SignInComponent} from "./sign-in/sign-in.component";

const routes: Routes = [
  {
    path: '', component: AuthComponent,
    children: [
      { path: '', redirectTo: 'signIn', pathMatch: 'full' },
      { path: 'signIn', component: SignInComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
