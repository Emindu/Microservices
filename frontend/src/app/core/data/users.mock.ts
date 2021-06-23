import {User} from "../models/User";

export const usersMockData : User [] = [
  {
    id: 1,
    username: 'josh',
    fullName: 'Josh Watson',
    about: 'Lead iOS Developer Apple Inc.',
    reputation: 123,
    dateAdded: new Date(2005, 10, 15),
    dataUpdated: new Date(2005, 10, 15),
    country: 'UK'
  },
  {
    id: 2,
    username: 'mark',
    fullName: 'Mark Jones',
    about: 'C++ developer and learning Java',
    reputation: 543,
    dateAdded: new Date(2008, 2, 15),
    dataUpdated: new Date(2008, 2, 15),
    country: 'US'
  },
  {
    id: 3,
    username: 'asanka',
    fullName: 'Asanka Jayaweera',
    about: 'Expert in Java, C++, TypeScript',
    reputation: 1044,
    dateAdded: new Date(2012, 2, 20),
    dataUpdated: new Date(2012, 2, 20),
    country: 'Sri Lanka'
  },
];

