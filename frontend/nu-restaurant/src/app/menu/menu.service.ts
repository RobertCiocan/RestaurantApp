import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { MenuItem } from './models/menu-item.model';

@Injectable({
  providedIn: 'root',
})
export class MenuService{
  private apiUrl = 'http://localhost:8085/api/v1';

  constructor(private http: HttpClient) {}

  menuItems: MenuItem[] = [];

  getMenuItemById(id: string): Observable<MenuItem> {
    const finalEndpoint = `${this.apiUrl}/produs/${id}`;
    return this.http.get<MenuItem>(finalEndpoint);
  }

  getMenuItemsByCategory(category: string | null): Observable<MenuItem[]> {
    const finalEndpoint = `${this.apiUrl}/produs?category=${category}`;
    return this.http.get<MenuItem[]>(finalEndpoint);
  }
  
}
