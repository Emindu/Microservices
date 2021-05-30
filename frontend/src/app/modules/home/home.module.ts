import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home-routing.module';
import {SharedModule} from "../../shared/shared.module";
import { QuestionListItemComponent } from './question-list-item/question-list-item.component';
import {MatCardModule} from "@angular/material/card";
import { QuestionListComponent } from './question-list/question-list.component';
import { PostComponent } from './post/post.component';
import { QuestionComponent } from './question/question.component';
import {FormsModule} from "@angular/forms";
import { AddQuestionComponent } from './add-question/add-question.component';
import { UserComponent } from './user/user.component';
import {PostListItemComponent} from "./post-list-item/post-list-item.component";

@NgModule({
  declarations: [
    HomeComponent,
    QuestionListItemComponent,
    QuestionListComponent,
    PostComponent,
    QuestionComponent,
    AddQuestionComponent,
    UserComponent,
    PostListItemComponent,
  ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        SharedModule,
        MatCardModule,
        FormsModule
    ],
  exports: [
    HomeComponent
  ],
  bootstrap: [HomeComponent]
})
export class HomeModule { }
