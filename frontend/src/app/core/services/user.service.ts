import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../models/User';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {map} from 'rxjs/operators';
import {mapToUser, UserDto} from '../dto/UserDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsersById(userIds: number[]): Observable<User[]> {
    return this.http.post<UserDto[]>(`${environment.API_USER_SERVICE}/users/`, userIds)
      .pipe(
        map(
          userDtos => userDtos.map(dto => mapToUser(dto))
        )
      );
  }

  getUsers(): Observable<User[]> {
    return this.http.get<UserDto[]>(`${environment.API_USER_SERVICE}/users/`)
      .pipe(
        map(
          userDtos => userDtos.map(dto => mapToUser(dto))
        )
      );
  }

  getUser(userId: string | number): Observable<User> {
    return this.http.get<UserDto>(`${environment.API_USER_SERVICE}/user/${userId}`)
      .pipe(
        map(
          userDto => mapToUser(userDto)
        )
      );
  }

  getCurrentUser(): Observable<User> {
    return this.getUser(1); // todo
  }

}
