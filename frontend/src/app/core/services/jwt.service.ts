import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor() { }

  getToken(): string {
    return window.localStorage.jwt;
  }

  saveToken(token: string): void {
    window.localStorage.jwt = token;
  }

  destroyToken(): void {
    window.localStorage.removeItem('jwt');
  }
}
