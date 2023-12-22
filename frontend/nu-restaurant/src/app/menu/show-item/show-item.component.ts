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
  menuItem!: MenuItem;

  constructor(private route: ActivatedRoute, private menuService: MenuService) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.menuItem = this.menuService.getMenuItem(id)!;
    }else
    {
      console.log("id is not ok");
    }
    // daca vrem /menu/item?id=1
    // this.route.queryParams.subscribe(params => {
    //   // Retrieve the 'id' parameter from the query parameters
    //   this.menuItem = this.menuService.getMenuItem(params['id'])!;
    // });
  }
}
