import {Question} from "./Question";
import {Post} from "./Post";

export interface Answer extends Post{
  question: Question;
}
