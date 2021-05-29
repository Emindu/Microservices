import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./home.component";
import {QuestionListComponent} from "./question-list/question-list.component";
import {QuestionComponent} from "./question/question.component";
import {AddQuestionComponent} from "./add-question/add-question.component";
import {UserComponent} from "./user/user.component";

const routes: Routes = [
  {
    path: '', component: HomeComponent,
    children: [
      { path: '', redirectTo: 'questions', pathMatch: 'full' },
      { path: 'questions', component: QuestionListComponent },
      { path: 'questions/:id', component: QuestionComponent },
      { path: 'new', component: AddQuestionComponent },
      { path: 'user/:id', component: UserComponent },
    ]
  }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class HomeRoutingModule { }
