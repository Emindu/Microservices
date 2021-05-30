import {Component, Input, OnInit} from '@angular/core';
import {Question} from "../../../core/models/Question";
import {FacadeService} from "../../../core/services/facade.service";
import {Answer} from "../../../core/models/Answer";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../../core/models/User";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  @Input('question') question: Question;
  answers: Answer[] = [];
  newAnswer: string;
  loggedInUser: User;

  constructor(
    private facadeService: FacadeService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {

    this.route.params
      .subscribe(params => {
        let questionId = params['id'];
        this.facadeService
          .questionService
          .getQuestion(questionId)
          .subscribe( question => {
            this.question = question;
          })
        this.facadeService
          .answerService
          .getAnswersOfQuestion(questionId)
          .subscribe( answers => {
            this.answers = answers;
          });
      });

      this.facadeService.userService.getCurrentUser().subscribe(
        user =>{
            this.loggedInUser = user;
          },
        error => {
          console.log(error);
        }
        );
  }

  onSubmit() {
    this.facadeService.answerService.submitAnswer(
      this.question.id,
      this.loggedInUser.id,
      this.newAnswer
    ).subscribe(newAnswer => {
      console.log("Submitted");
      this.answers = [...this.answers, newAnswer];
      this.newAnswer = null;
    }, error => {
      console.log(error);
    });
  }

  isAnswerEmpty() {
    return this.newAnswer == null || this.newAnswer.trim().length < 1;
  }
}
