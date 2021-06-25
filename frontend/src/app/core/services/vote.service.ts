import { Injectable } from '@angular/core';
import {vote_state_t} from "../types/vote-state.type";
import {Observable, of} from "rxjs";
import {Post} from "../models/Post";
import {instanceOfQuestion} from "../models/Question";
import {instanceOfAnswer} from "../models/Answer";

@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor() { }

  getPostVoteStateOfPostForUser(post: Post, userId: string | number) : Observable<vote_state_t> {
    if (instanceOfQuestion(post)) {
      return this.getQuestionVoteState(post.id, userId);
    }else if(instanceOfAnswer(post)){
      return this.getAnswerVoteState(post.id, userId);
    }
    return of(0);
  }

  getQuestionVoteState(questsionId: string | number, userId): Observable<vote_state_t> {
    //TODO();
    let vote: vote_state_t;
    switch (Math.floor((questsionId + userId) % 3)){
      case 1:
        vote = 1;
        break;
      case 2:
        vote = -1;
        break;
      default:
        vote = 0;
    }
    return of(vote);
  }

  getAnswerVoteState(answerId: string | number, userId): Observable<vote_state_t> {
    //TODO ()
    let vote: vote_state_t;
    switch (Math.floor((answerId + userId) % 3)){
      case 1:
        vote = 1;
        break;
      case 2:
        vote = -1;
        break;
      default:
        vote = 0;
    }
    return of(vote);
  }
}
