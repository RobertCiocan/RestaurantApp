import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './models/user.model';
import { ClientRequest } from './models/Requests/client_request';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  // ex de api pt a testa 
  private apiUrl = 'https://jsonplaceholder.typicode.com/users';
  private apiUrlR ='http://localhost:8084/api';
  constructor(private http: HttpClient) {}

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  createClient(registration: ClientRequest): Observable<ClientRequest> {
    return this.http.post<ClientRequest>(`${this.apiUrlR}/create_client`, registration);
  }

  signInUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }
}
