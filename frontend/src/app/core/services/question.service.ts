import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {Question} from "../models/Question";
import {questionsMockData} from "../data/questions.mock";
import {usersMockData} from "../data/users.mock";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor() { }

  getQuestions(): Observable<Question[]> {
    return of(questionsMockData);
  }

  getQuestionsOfUser(userId: number | string): Observable<Question[]> {
    return of(questionsMockData.filter(question => question.user.id == userId));
  }

  getQuestion(questionId: string | number): Observable<Question> {
    return of(questionsMockData.filter(question => question.id == questionId).pop());
  }

  submitQuestion(userId: string | number, title: string, text: string): Observable<Question> {
    let id = Math.max(...questionsMockData.map(ans => Number(ans.id))) + 1;
    let newQuestion: Question = {
      id,
      title,
      text,
      votesCount: 0,
      answersCount: 0,
      dateAdded: new Date(),
      dateUpdated: new Date(),
      user: usersMockData.filter(user => user.id = userId).pop(),
    };
    return of(newQuestion);
  }
}
