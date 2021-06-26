import {Injectable} from '@angular/core';
import {vote_state_t} from '../types/vote-state.type';
import {Observable, of} from 'rxjs';
import {Post} from '../models/Post';
import {instanceOfQuestion} from '../models/Question';
import {instanceOfAnswer} from '../models/Answer';
import {HttpClient} from '@angular/common/http';
import {VoteStateDto} from '../dto/VoteStateDto';
import {map} from 'rxjs/operators';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor(
    private http: HttpClient,
  ) { }

  getPostVoteStateOfPostForUser(post: Post, userId: string | number): Observable<vote_state_t> {
    if (instanceOfQuestion(post)) {
      return this.getQuestionVoteState(post.id, userId);
    }else if (instanceOfAnswer(post)){
      return this.getAnswerVoteState(post.id, userId);
    }
    return of(0);
  }

  setPostVoteStateOfPostForUser(post: Post, userId: string | number, vote: vote_state_t): Observable<vote_state_t> {
    if (instanceOfQuestion(post)) {
      return this.setQuestionVoteState(post.id, userId, vote);
    }else if (instanceOfAnswer(post)){
      return this.setAnswerVoteState(post.id, userId, vote);
    }
    return of(0);
  }

  $getVote(url: string): Observable<vote_state_t> {
    return this.http.get<VoteStateDto>(url)
      .pipe(
        map(voteDto => {
          return voteDto.vote;
        })
      );
  }
  $setVote(url: string): Observable<vote_state_t> {
    return this.http.post<VoteStateDto>(url, {})
      .pipe(
        map(voteDto => {
          return voteDto.vote;
        })
      );
  }

  getQuestionVoteState(questionId: string | number, userId): Observable<vote_state_t> {
    return this.$getVote(`${environment.API_VOTE_SERVICE}/votes/state/user/${userId}/question/${questionId}`);
  }

  getAnswerVoteState(answerId: string | number, userId): Observable<vote_state_t> {
    return this.$getVote(`${environment.API_VOTE_SERVICE}/votes/state/user/${userId}/answer/${answerId}`);
  }

  setQuestionVoteState(questionId: string | number, userId, vote: vote_state_t): Observable<vote_state_t> {
    return this.$setVote(
      `${environment.API_VOTE_SERVICE}/votes/vote/user/${userId}/question/${questionId}/vote/${vote}`);
  }

  setAnswerVoteState(answerId: string | number, userId, vote: vote_state_t): Observable<vote_state_t> {
    return this.$setVote(
      `${environment.API_VOTE_SERVICE}/votes/vote/user/${userId}/answer/${answerId}/vote/${vote}`);
  }
}
