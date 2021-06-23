import {User} from "./User";
export interface Post {
  id: number | string;
  text: string
  user: User,
  dateAdded: Date;
  dateUpdated: Date;
  votesCount: number,
}
