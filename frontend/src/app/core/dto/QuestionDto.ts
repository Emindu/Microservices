import { Question } from '../models/Question';
import {mapToUser, UserDto} from './UserDto';
import {instanceOfUserDto, User} from '../models/User';

export interface QuestionDto {
    questionId: number;
    text: string;
    title: string;

    createdBy: number;
    lastModifiedBy: number;
    createdDate: Date;
    lastModifiedDate;

    answersCount: number;
    votesCount: number;
}

export function mapToQuestion(questionDto: QuestionDto, user: User | UserDto): Question{
  return {
    ...questionDto,
    id: questionDto.questionId,
    dateAdded: questionDto.createdDate,
    dateUpdated: questionDto.lastModifiedDate,
    user: instanceOfUserDto(user) ? user : mapToUser(user)
  };
}
