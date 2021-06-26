import {Component,  OnInit} from '@angular/core';
import {Question} from '../../../core/models/Question';
import {FacadeService} from '../../../core/services/facade.service';
import {Answer} from '../../../core/models/Answer';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../../core/models/User';
import {mergeMap} from 'rxjs/operators';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  question: Question = null;
  answers: Answer[] = null;
  newAnswer: string;
  loggedInUser: User = null;
  loadedQuestions = false;
  loadedAnswers = false;

  constructor(
    private facadeService: FacadeService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {

    this.route.params.pipe(
      mergeMap(params => params.id as string),
      mergeMap(questionId => this.facadeService.questionService.getQuestion(questionId)),
      mergeMap(question => {
        this.question = question;
        if (this.question !== null){
          this.loadedQuestions = true;
        }
        return this.facadeService.answerService.getAnswersOfQuestion(question.id);
      })
    ).subscribe(
      answers => {
        this.answers = answers;
        if (this.answers !== null){
          this.loadedAnswers = true;
        }
      },
      error => {
        console.log(error);
      }
    );

    this.facadeService.userService.getCurrentUser().subscribe(
        user => {
            this.loggedInUser = user;
          },
        error => {
          console.log(error);
        });
  }

  onSubmit(): void {
    this.facadeService.answerService.submitAnswer(
      this.question.id,
      this.loggedInUser.id,
      this.newAnswer
    ).subscribe(newAnswer => {
      console.log('Submitted');
      this.answers = [...this.answers, newAnswer];
      this.newAnswer = null;
    }, error => {
      console.log(error);
    });
  }

  isAnswerEmpty(): boolean {
    return this.newAnswer == null || this.newAnswer.trim().length < 1;
  }
}
