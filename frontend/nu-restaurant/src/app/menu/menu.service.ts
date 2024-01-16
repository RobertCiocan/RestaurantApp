import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { MenuItem } from './models/menu-item.model';

@Injectable({
  providedIn: 'root',
})
export class MenuService{
  private apiUrl = 'http://localhost:8086/api/v1';

  constructor(private http: HttpClient) {}

  menuItems: MenuItem[] = [];

  getMenuItemById(id: string): Observable<MenuItem> {
    return this.http.get<MenuItem>(`${this.apiUrl}/menu/${id}`);
  }

  getMenuItemsByCategory(category: string | null): Observable<MenuItem[]> {
    return this.http.get<any[]>(`${this.apiUrl}/menu/${category}`);
  }
  
}
