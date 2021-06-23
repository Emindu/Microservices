import {Answer} from "../models/Answer";
import {usersMockData} from "./users.mock";
import {questionsMockData} from "./questions.mock";

export const answersMockData : Answer[] = [
  {
    id: 1,
    question: questionsMockData[0],
    user: usersMockData[1],
    text: 'A reference can be thought of as a constant pointer (not to be confused with a pointer to a constant value!) with automatic indirection, ie the compiler will apply the * operator for you.\n' +
      '\n' +
      'All references must be initialized with a non-null value or compilation will fail. It\'s neither possible to get the address of a reference - the address operator will return the address of the referenced value instead - nor is it possible to do arithmetics on references.\n' +
      '\n' +
      'C programmers might dislike C++ references as it will no longer be obvious when indirection happens or if an argument gets passed by value or by pointer without looking at function signatures.\n' +
      '\n' +
      'C++ programmers might dislike using pointers as they are considered unsafe - although references aren\'t really any safer than constant pointers except in the most trivial cases - lack the convenience of automatic indirection and carry a different semantic connotation.',
    dateAdded: new Date(2019, 4, 15),
    dateUpdated: new Date(2019, 4, 15),
    votesCount: 14,
  },
  {
    id: 2,
    question: questionsMockData[0],
    user: usersMockData[2],
    text: 'Apart from syntactic sugar, a reference is a const pointer (not pointer to a const). You must establish what it refers to when you declare the reference variable, and you cannot change it later.\n' +
      '\n' +
      'Update: now that I think about it some more, there is an important difference.\n' +
      '\n' +
      'A const pointer\'s target can be replaced by taking its address and using a const cast.\n' +
      '\n' +
      'A reference\'s target cannot be replaced in any way short of UB.\n' +
      '\n' +
      'This should permit the compiler to do more optimization on a reference.',
    dateAdded: new Date(2020, 2, 15),
    dateUpdated: new Date(2020, 4, 15),
    votesCount: 20,
  },
  {
    id: 3,
    question: questionsMockData[1],
    user: usersMockData[0],
    text: '(Caveat: I am not a Java programmer, I am a Perl programmer. Perl has no formal protections which is perhaps why I understand the problem so well :) )\n' +
      '\n' +
      'Private:\n' +
      'Like you\'d think, only the class in which it is declared can see it.\n' +
      '\n' +
      'Package Private:\n' +
      'It can only be seen and used by the package in which it was declared. This is the default in Java (which some see as a mistake).\n' +
      '\n' +
      'Protected:\n' +
      'Package Private + can be seen by subclasses or package members.\n' +
      '\n' +
      'Public:\n' +
      'Everyone can see it.',
    dateAdded: new Date(2020, 2, 15),
    dateUpdated: new Date(2020, 4, 15),
    votesCount: 34,
  },
  {
    id: 4,
    question: questionsMockData[1],
    user: usersMockData[2],
    text: 'Easy rule. Start with declaring everything private. And then progress towards the public as the needs arise and design warrants it.\n' +
      '\n' +
      'When exposing members ask yourself if you are exposing representation choices or abstraction choices. The first is something you want to avoid as it will introduce too many dependencies on the actual representation rather than on its observable behavior.\n' +
      '\n' +
      'As a general rule I try to avoid overriding method implementations by subclassing; it\'s too easy to screw up the logic. Declare abstract protected methods if you intend for it to be overridden.\n' +
      '\n' +
      'Also, use the @Override annotation when overriding to keep things from breaking when you refactor.',
    dateAdded: new Date(2019, 6, 25),
    dateUpdated: new Date(2020, 8, 14),
    votesCount: 23,
  },
]
