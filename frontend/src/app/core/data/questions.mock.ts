import {usersMockData} from "./users.mock";
import {Question} from "../models/Question";

export const questionsMockData : Question[] = [
  {
    id: 1,
    title: 'What are the differences between a pointer variable and a reference variable in C++?',
    text: 'I know references are syntactic sugar, so code is easier to read and write.\n\nBut what is the difference between a pointer variable and a reference variable?',
    user: usersMockData[0],
    dateAdded: new Date(2014, 10, 15),
    dateUpdated: new Date(2015, 4, 12),
    votesCount: 23,
    answersCount: 2
  },
  {
    id: 2,
    title: 'What is the difference between public, protected, package-private and private in Java?',
    text: 'In Java, are there clear rules on when to use each of access modifiers, namely the default (package private), public, protected and private, while making class and interface and dealing with inheritance?',
    user: usersMockData[1],
    dateAdded: new Date(2016, 8, 11),
    dateUpdated: new Date(2020, 4, 30),
    votesCount: 15,
    answersCount: 2
  },
]
