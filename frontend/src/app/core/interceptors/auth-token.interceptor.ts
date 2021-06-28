import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {JwtService} from '../services/jwt.service';

@Injectable()
export class AuthTokenInterceptor implements HttpInterceptor {

  constructor(private jwtService: JwtService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const headersConfig: { [key: string]: string; } = {
      'Content-Type': 'application/json',
      // tslint:disable-next-line:object-literal-key-quotes
      'Accept': 'application/json'
    };

    const token = this.jwtService.getToken();

    if (token) {
      headersConfig.Authorization = `Bearer ${token}`;
    }

    request = request.clone({ setHeaders: headersConfig });
    return next.handle(request);
  }


}
