import {Question} from './Question';
import {Post} from './Post';

export interface Answer extends Post{
  question: Partial<Question>;
}

export function instanceOfAnswer(post: Post): post is Answer {
  return 'question' in post || !('title' in post);
}
