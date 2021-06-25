import { Component, OnInit } from '@angular/core';
import {Question} from "../../../core/models/Question";
import {FacadeService} from "../../../core/services/facade.service";

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.scss']
})
export class QuestionListComponent implements OnInit {
  questionList: Question[];

  constructor(private facadeService: FacadeService) { }

  ngOnInit(): void {
    this.facadeService.questionService.getQuestions().subscribe(
      questions => {
        this.questionList = questions;
      },
      error => {
        console.log(error)
      }
    );
  }

}
