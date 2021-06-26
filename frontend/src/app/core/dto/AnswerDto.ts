import { Answer } from '../models/Answer';
import {mapToUser, UserDto} from './UserDto';

export interface AnswerDto{
    answerId: number;
    text: string;
    questionId: number;

    createdBy: number;
    lastModifiedBy: number;
    createdDate: Date;
    lastModifiedDate: Date;

    votesCount: number;
}

export function mapToAnswer(answerDto: AnswerDto, userDto: UserDto): Answer{
  return {
    id: answerDto.answerId,
    dateAdded: answerDto.createdDate,
    dateUpdated: answerDto.lastModifiedDate,
    text: answerDto.text,
    votesCount: answerDto.votesCount,
    question: null, // todo
    user: mapToUser(userDto)
  };
}
