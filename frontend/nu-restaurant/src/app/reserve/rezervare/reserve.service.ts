import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reserve } from '../models/reserve.model';


@Injectable({
  providedIn: 'root'
})
export class ReserveService {
  private apiUrl = 'http://localhost:8083/api/v1/rezervare';
  constructor(private http: HttpClient) {}

  createReserve(reserveRequest: Reserve):Observable<Reserve>{
    return this.http.post<Reserve>(`${this.apiUrl}/create`, reserveRequest);
  }

  getReservations(): Observable<Reserve[]> {
    return this.http.get<Reserve[]>(this.apiUrl);
  }

  updateReserve(reservationData: Reserve): Observable<Reserve> {
    return this.http.put<Reserve>(this.apiUrl, reservationData);
  }

  getReserveById(reservationId: string): Observable<Reserve[]> {
    const url = `${this.apiUrl}/masa/${reservationId}`;
    return this.http.get<Reserve[]>(url);
  }


  deleteReserve(reservationId: string): Observable<void> {
    const url = `${this.apiUrl}/${reservationId}`;
    return this.http.delete<void>(url);
  }

}
