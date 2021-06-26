import { Question } from '../models/Question';
import {User} from '../models/User';

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

export function mapToQuestion(questionDto: QuestionDto, user: User ): Question{
  return {
    ...questionDto,
    id: questionDto.questionId,
    dateAdded: questionDto.createdDate,
    dateUpdated: questionDto.lastModifiedDate,
    user
  };
}
