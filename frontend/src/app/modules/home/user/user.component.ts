import { Component, OnInit } from '@angular/core';
import {User} from "../../../core/models/User";
import {FacadeService} from "../../../core/services/facade.service";
import {ActivatedRoute} from "@angular/router";
import {Question} from "../../../core/models/Question";
import {Answer} from "../../../core/models/Answer";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  user: User;
  questions: Question[] = [];
  answers: Answer[] = [];

  constructor(
    private facadeService: FacadeService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.route.params
      .subscribe(params => {
        let userId = params['id'];
        this.facadeService.userService.getUser(userId).subscribe(user =>{
          this.user = user;
          this.facadeService.questionService.getQuestionsOfUser(user.id).subscribe(
            questions => {
              this.questions = questions
            },
            error => {
              console.log(error)
            });
          this.facadeService.answerService.getAnswersOfUser(user.id).subscribe(
            answers => {
              this.answers = answers
            },
            error => {
              console.log(error)
            });
        });

      });
  }

}
