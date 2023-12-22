import { TestBed } from '@angular/core/testing';

import { MenuService } from './menu.service';

describe('UsersService', () => {
  let service: MenuService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenuService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
