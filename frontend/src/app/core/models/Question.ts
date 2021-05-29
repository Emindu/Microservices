import {Post} from "./Post";

export interface Question extends Post{
  title: string;
  answersCount: number,
}
