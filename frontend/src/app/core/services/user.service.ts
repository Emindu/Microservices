import { Injectable } from '@angular/core';
import {BehaviorSubject,  Observable, of, ReplaySubject, zip} from 'rxjs';
import {User} from '../models/User';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {catchError, distinctUntilChanged, map, mergeMap} from 'rxjs/operators';
import {mapToUser, UserDto} from '../dto/UserDto';
import {FacadeService} from './facade.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private currentUserSubject = new BehaviorSubject<User>({} as User);
  public currentUser = this.currentUserSubject.asObservable().pipe(distinctUntilChanged());

  private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
  public isAuthenticated = this.isAuthenticatedSubject.asObservable();

  constructor(
    private http: HttpClient,
    private facadeService: FacadeService
  ) { }

  getUsersById(userIds: number[]): Observable<User[]> {
    if ( (userIds || []).length === 0 ){
      return of([]);
    }
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
    return of(this.currentUserSubject.value);
  }

  populate(): void {
    const jwt = this.facadeService.jwtService.getToken();
    if (jwt) {
      this.facadeService.authService.getUser(jwt).pipe(
        map(data => data.username),
        mergeMap(username => this.facadeService.userService.getUser(username))
      )
        .subscribe(
          user => this.setAuth(user, jwt),
          err => {
            console.log(err);
            this.purgeAuth();
          }
        );
    } else {
      this.purgeAuth();
    }
  }

  setAuth(user: User, token: string): void {
    this.facadeService.jwtService.saveToken(token);
    this.currentUserSubject.next(user);
    this.isAuthenticatedSubject.next(true);
  }

  purgeAuth(): void {
    this.facadeService.jwtService.destroyToken();
    this.currentUserSubject.next({} as User);
    this.isAuthenticatedSubject.next(false);
  }

  attemptAuth(username: string, password: string): Observable<boolean>{
      return this.facadeService.authService.login(username, password)
      .pipe(
        map(data => data.jwt),
        mergeMap(jwt => zip(
          this.facadeService.userService.getUser(username), of(jwt)
        )),
        map(
          ([user, jwt]) => {
            this.setAuth(user, jwt);
            return true;
          },
        ),
        catchError(
          err => {
            console.log(err);
            this.purgeAuth();
            return of(false);
          }
        )
      );
  }
}
