import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {JwtDto} from '../dto/JwtDto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
  ) { }


  login(username: string, password: string): Observable<JwtDto> {
    return this.http.post<JwtDto>(`${environment.API_BASE}/login`, { username, password });
  }

  getUser(jwt: string): Observable<{ username: string }> {
    return this.http.post<{ username: string }>(`${environment.API_BASE}/user`, { jwt });
  }
}
