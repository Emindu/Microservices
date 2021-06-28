import {Injectable, Injector, Type} from '@angular/core';
import {AnswerService} from './answer.service';
import {QuestionService} from './question.service';
import {UserService} from './user.service';
import {VoteService} from './vote.service';
import {AuthService} from './auth.service';
import {JwtService} from './jwt.service';

@Injectable({
  providedIn: 'root'
})
// tslint:disable:variable-name
export class FacadeService {

  private _answerService: AnswerService;
  private _questionService: QuestionService;
  private _userService: UserService;
  private _voteService: VoteService;
  private _authService: AuthService;
  private _jwtService: JwtService;

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

  get voteService(): VoteService {
    if (!this._voteService) {
    this._voteService = this.injector.get<VoteService>(VoteService as Type<VoteService>);
  }
    return this._voteService;
  }

  get authService(): AuthService {
    if (!this._authService) {
      this._authService = this.injector.get<AuthService>(AuthService as Type<AuthService>);
    }
    return this._authService;
  }

  get jwtService(): JwtService {
    if (!this._jwtService) {
      this._jwtService = this.injector.get<JwtService>(JwtService as Type<JwtService>);
    }
    return this._jwtService;
  }
}
