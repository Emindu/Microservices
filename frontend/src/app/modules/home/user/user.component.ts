import { Component, OnInit } from '@angular/core';
import {User} from '../../../core/models/User';
import {FacadeService} from '../../../core/services/facade.service';
import {ActivatedRoute} from '@angular/router';
import {Question} from '../../../core/models/Question';
import {Answer} from '../../../core/models/Answer';
import {mergeMap} from 'rxjs/operators';
import {zip} from 'rxjs';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  user: User;
  questions: Question[] = null;
  answers: Answer[] = null;
  loadedUser = false;
  loadedQuestionsAndAnswers = false;

  constructor(
    private facadeService: FacadeService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {

    this.route.params.pipe(
      mergeMap(params => params.id as string),
      mergeMap(userId => this.facadeService.userService.getUser(userId)),
      mergeMap(user => {
        this.user = user;
        if (this.user !== null){
          this.loadedUser = true;
        }
        return zip(
          this.facadeService.questionService.getQuestionsOfUser(user.id),
          this.facadeService.answerService.getAnswersOfUser(user.id),
        );
      })
    ).subscribe(
      ([questions, answers]) => {
        this.answers = answers;
        this.questions = questions;
        this.loadedQuestionsAndAnswers = true;
      },
      error => {
        console.log(error);
      }
    );
  }

}
