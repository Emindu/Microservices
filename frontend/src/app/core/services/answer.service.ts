import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {Answer} from "../models/Answer";
import {answersMockData} from "../data/answers.mock";
import {usersMockData} from "../data/users.mock";
import {questionsMockData} from "../data/questions.mock";

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor() { }

  getAnswersOfQuestion(questionId: number | string): Observable<Answer[]> {
    return of(answersMockData.filter(answer => answer.question.id == questionId));
  }

  getAnswersOfUser(userId: number | string): Observable<Answer[]> {
    return of(answersMockData.filter(answer => answer.user.id == userId));
  }

  getAnswer(answerId: string | number): Observable<Answer> {
    return of(answersMockData.filter(ans => ans.id == answerId).pop());
  }

  submitAnswer(questionId: string | number, userId: string | number, text: string): Observable<Answer> {
    let id = Math.max(...answersMockData.map(ans => Number(ans.id))) + 1;
    let newAnswer: Answer = {
      id,
      votesCount: 0,
      dateAdded: new Date(),
      dateUpdated: new Date(),
      text,
      user: usersMockData.filter(user => user.id == userId).pop(),
      question: questionsMockData.filter(ques => ques.id == questionId).pop(),
    };
    return of(newAnswer);
  }
}
