import {Injectable, Injector, Type} from '@angular/core';
import {AnswerService} from "./answer.service";
import {QuestionService} from "./question.service";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class FacadeService {

  private _answerService: AnswerService;
  private _questionService: QuestionService;
  private _userService: UserService;

  constructor(private injector: Injector) { }

  get answerService(): AnswerService {
    if (!this._answerService) {
      this._answerService = this.injector.get<AnswerService>(AnswerService as Type<AnswerService>);
    }
    return this._answerService;
  }

  get questionService(): QuestionService {
    if (!this._questionService) {
      this._questionService = this.injector.get<QuestionService>(QuestionService as Type<QuestionService>);
    }
    return this._questionService;
  }

  get userService(): UserService {
    if (!this._userService) {
    this._userService = this.injector.get<UserService>(UserService as Type<UserService>);
  }
    return this._userService;
  }
}
