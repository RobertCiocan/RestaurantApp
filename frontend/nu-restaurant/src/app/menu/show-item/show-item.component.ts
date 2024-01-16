import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { MenuService } from '../menu.service';
import { MenuItem } from '../models/menu-item.model';

@Component({
  selector: 'app-show-item',
  templateUrl: './show-item.component.html',
  styleUrls: ['./show-item.component.scss']
})
export class ShowItemComponent implements OnInit {
  menuItem: MenuItem | undefined;

  constructor(private route: ActivatedRoute, private menuService: MenuService) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.menuService.getMenuItemById(id).subscribe(
        (item: MenuItem) => {
          this.menuItem = item;
        },
        (error) => {
          console.error('Error fetching menu item', error);
        }
      );
    } else {
      console.log("id is not ok");
    }
  }
}
