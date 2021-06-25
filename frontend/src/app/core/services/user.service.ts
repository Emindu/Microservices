import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {User} from "../models/User";
import {usersMockData} from "../data/users.mock";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getUsers(): Observable<User[]> {
    return of(usersMockData);
  }

  getUser(userId: string | number): Observable<User> {
    return of(usersMockData.filter(user => user.id == userId).pop());
  }

  getCurrentUser(): Observable<User> {
    return this.getUser(1);
  }

}
