import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeRoutingModule} from "../home/home-routing.module";
import { AuthComponent } from './auth.component';
import { SignInComponent } from './sign-in/sign-in.component';
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [AuthComponent, SignInComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule
  ],
  exports: [
    AuthComponent
  ],
  bootstrap: [AuthComponent]
})
export class AuthModule { }
