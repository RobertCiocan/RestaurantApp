import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { ActivatedRoute } from '@angular/router';
import { MenuService } from '../menu.service';
import { MenuItem } from '../models';

@Component({
  selector: 'app-produs',
  templateUrl: './produs.component.html',
  styleUrls: ['./produs.component.scss']
})
export class ProdusComponent implements OnInit {
  constructor(private route: ActivatedRoute, private menuService: MenuService) {}

  prodList: Observable<MenuItem[]> = new Observable<MenuItem[]>();

  ngOnInit(): void {
    const name = this.route.snapshot.paramMap.get('name');

    this.prodList = this.menuService.getMenuItemsByCategory(name);
  }
}
