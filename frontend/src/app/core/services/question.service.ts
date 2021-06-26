import {Injectable} from '@angular/core';
import {combineLatest, Observable, of} from 'rxjs';
import {Question} from '../models/Question';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {mapToQuestion, QuestionDto} from '../dto/QuestionDto';
import {map, mergeMap, tap} from 'rxjs/operators';
import {UserService} from './user.service';
import {User} from '../models/User';
import {mapToUser, UserDto} from '../dto/UserDto';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient, private userService: UserService) {
  }

  $getQuestions(url: string): Observable<Question[]> {
    return this.http.get<QuestionDto[] | QuestionDto>(url)
      .pipe(
        map(questionDtoList => Array.isArray(questionDtoList) ? questionDtoList : [questionDtoList]),
        mergeMap(questionDtoList => combineLatest(
          of(questionDtoList),
          this.userService.getUsersById(questionDtoList.map(dto => dto.createdBy))
        )),
        map(([questionsDtoList, users]) =>
          questionsDtoList.map(dto => mapToQuestion(dto, users.filter(user => (+user.id) === dto.createdBy).pop()))
        ),
        tap(console.log)
      )
  }

  getPartialQuestionById(questionIds: number[]): Observable<Partial<Question>[]> {
    if ( (questionIds || []).length === 0 ){
      return of([]);
    }
    return this.http.post<QuestionDto[]>(`${environment.API_QUESTION_SERVICE}/questions/by/id`, questionIds)
      .pipe(
        map(
          userDtos => userDtos.map(dto => {
            return {
              id : dto.questionId,
              title: dto.title
            };
          })
        )
      );
  }

  getQuestions(): Observable<Question[]> {
    return this.$getQuestions(`${environment.API_QUESTION_SERVICE}/questions/`);
  }

  getQuestionsOfUser(userId: number | string): Observable<Question[]> {
    return this.$getQuestions(`${environment.API_QUESTION_SERVICE}/questions/user/${userId}/`);
  }

  getQuestion(questionId: string | number): Observable<Question> {
    return this.$getQuestions(`${environment.API_QUESTION_SERVICE}/questions/${questionId}/`).pipe(
      map(questions => {
          if (questions.length === 1) {
            return questions.pop();
          } else {
            throw new Error();
          }
        }
      ),
      tap(console.log)
    );
  }

  submitQuestion(userId: string | number, title: string, text: string): Observable<Question> {
    const questionBody: Partial<QuestionDto> = {
      title,
      text,
      createdBy: +userId
    };

    return this.http.post<QuestionDto>(`${environment.API_QUESTION_SERVICE}/questions/`, questionBody)
      .pipe(
        mergeMap(questionsDto => combineLatest(
          of(questionsDto),
          this.userService.getUser(questionsDto.createdBy)
        )),
        map(([questionsDto, user]) =>
          mapToQuestion(questionsDto, user)
        )
      );
  }
}
