import { Answer } from '../models/Answer';
import {User} from '../models/User';
import {Question} from '../models/Question';

export interface AnswerDto{
    answerId: number;
    text: string;
    questionId: number;

    createdBy: number;
    lastModifiedBy: number;
    createdDate: Date;
    lastModifiedDate: Date;

    voteCount: number;
}

export function mapToAnswer(answerDto: AnswerDto, user: Partial<User>, question: Partial<Question>): Answer{
  return {
    id: answerDto.answerId,
    dateAdded: answerDto.createdDate,
    dateUpdated: answerDto.lastModifiedDate,
    text: answerDto.text,
    votesCount: answerDto.voteCount || 0,
    user,
    question
  };
}
