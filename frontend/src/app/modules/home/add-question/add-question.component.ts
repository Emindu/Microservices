import { Component, OnInit } from '@angular/core';
import {FacadeService} from "../../../core/services/facade.service";
import {User} from "../../../core/models/User";

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.scss']
})
export class AddQuestionComponent implements OnInit {

  newQuestionTitle: string;
  newQuestionText: string;
  loggedInUser: User;

  constructor(
    private facadeService: FacadeService,
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.facadeService.questionService.submitQuestion(
      this.loggedInUser.id,
      this.newQuestionTitle,
      this.newQuestionText
    ).subscribe(newQuestion => {
      console.log("Submitted", newQuestion);
    }, error => {
      console.log(error);
    });
  }

  isQuestionEmpty() {
    return (
      this.newQuestionTitle == null ||
      this.newQuestionText == null ||
      this.newQuestionTitle.trim().length < 1 ||
      this.newQuestionText.trim().length < 1
    );
  }
}
