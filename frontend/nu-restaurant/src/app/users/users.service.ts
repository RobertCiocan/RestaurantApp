import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { User } from './models/user.model';
import { AuthenticationRequest } from './models/authentication-request';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  // private apiUrl = 'http://localhost:8080/idm-service/api/v1';
  private apiUrl = 'http://localhost:8086/api/v1';

  constructor(private http: HttpClient) {}

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/register`, user);
  }

  signInUser(authenticationRequest: AuthenticationRequest): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/login`, authenticationRequest);
  }

  logoutUser(jwt: string | null): Observable<string> {
    const headers = jwt
      ? new HttpHeaders({ Authorization: `Bearer ${jwt}` })
      : new HttpHeaders();
      
    return this.http.post<string>(`${this.apiUrl}/logout`, null, { headers, observe: 'response' }).pipe(
      catchError((error) => {
        console.error('Error logging out', error);
        return throwError(error);
      }),
      map((response: HttpResponse<string>) => {
        if (response.status === 200) {
          sessionStorage.removeItem('jwt');
        }
        return response.body || '';
      })
    );
  }
}
