import { Injectable } from '@angular/core';
import { MenuItem } from './models/menu-item.model';

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  private menuItems: MenuItem[] = [
    {
      id: '1',
      name: 'Pizza',
      description: 'Peperoni Pizza',
      price: 30,
      image: 'pizza.png',
    },
    {
      id: '2',
      name: 'AAAAAAAAAAAA',
      description: 'Peperoni Pizza',
      price: 30,
      image: 'pizza.png',
    },
  ];

  getMenuItem(id: string): MenuItem | undefined {
    return this.menuItems.find((item) => item.id === id);
  }
}
