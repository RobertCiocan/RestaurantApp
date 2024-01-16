import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { User } from './models/user.model';
import { AuthenticationRequest } from './models/Requests/authentication-request';
import { RegistrationRequest } from './models/Requests/registration-request';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  // private apiUrl = 'http://localhost:8080/idm-service/api/v1';
  public apiUrl = 'http://localhost:8086/api/v1';

  constructor(private http: HttpClient) {}

  registerUser(registrationRequest: RegistrationRequest): Observable<String> {
    return this.http.post<string>(`${this.apiUrl}/register`, registrationRequest);
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
