import {Post} from "./Post";

export interface Question extends Post{
  title: string;
  answersCount: number,
}

export function instanceOfQuestion(post: Post): post is Question {
  return 'title' in post;
}
