import {User} from '../models/User';

export interface UserDto {
  userId: number;
  name: string;
  country: string;
  reputation: number;
}

export function mapToUser(dto: UserDto): User{
  return {
    ...dto,
    id: dto.userId,
    fullName: dto.name,
    username: '' + dto.userId
  };
}



