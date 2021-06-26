import {Injectable} from '@angular/core';
import {combineLatest,  Observable, of} from 'rxjs';
import {Answer} from '../models/Answer';
import {HttpClient} from '@angular/common/http';
import {UserService} from './user.service';
import {map, mergeMap, tap} from 'rxjs/operators';
import {AnswerDto, mapToAnswer} from '../dto/AnswerDto';
import {environment} from '../../../environments/environment';
import {QuestionService} from './question.service';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(
    private http: HttpClient,
    private userService: UserService,
    private questionService: QuestionService
  ) {
  }

  $getAnswers(url: string): Observable<Answer[]> {
    return this.http.get<AnswerDto[] | AnswerDto>(url)
      .pipe(
        map(AnswerDtoList =>
          AnswerDtoList == null ? [] : Array.isArray(AnswerDtoList) ? AnswerDtoList : [AnswerDtoList]
        ),
        mergeMap(AnswerDtoList => combineLatest(
          of(AnswerDtoList),
          this.userService.getUsersById(AnswerDtoList.map(dto => dto.createdBy)),
          this.questionService.getPartialQuestionById(AnswerDtoList.map(dto => dto.questionId))
        )),
        map(([AnswerDtoList, users, questions]) =>
            AnswerDtoList.map(dto => mapToAnswer(
              dto,
              users.filter(user => (+user.id) === dto.createdBy).pop(),
              questions.filter(question => question.id === dto.questionId).pop()
              )
            )
        ),
        tap(console.log)
      );
  }

  getAnswersOfQuestion(questionId: number | string): Observable<Answer[]> {
    return this.$getAnswers(`${environment.API_ANSWER_SERVICE}/answers/question/${questionId}/`);
  }

  getAnswersOfUser(userId: number | string): Observable<Answer[]> {
    return this.$getAnswers(`${environment.API_ANSWER_SERVICE}/answers/user/${userId}/`);
  }


  submitAnswer(questionId: string | number, userId: string | number, text: string): Observable<Answer> {
    const answerBody: Partial<AnswerDto> = {
      questionId: +questionId,
      createdBy: +userId,
      text
    };

    return this.http.post<AnswerDto>(`${environment.API_ANSWER_SERVICE}/answers/`, answerBody)
      .pipe(
        mergeMap(answerResult =>
          combineLatest(
            of(answerResult),
            this.userService.getUser(answerResult.createdBy)
          )
        ),
        map(([answerDto, user]) =>
          mapToAnswer(answerDto, user, {id: questionId})
        )
      );
  }
}
