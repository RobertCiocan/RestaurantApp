import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UsersService } from './users.service';
import { User } from './models/user.model';
import { AuthenticationRequest } from './models/Requests/authentication-request';

describe('UsersService', () => {
  let service: UsersService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(UsersService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should register a user', () => {
    const mockUser: User = {
      username: 'testuser',
      email: 'test@example.com',
      password: 'password123',
      firstName: 'Test',
      lastName: 'User',
      phoneNumber: '123456789',
      address: 'Test Address',
    };

    service.registerUser(mockUser).subscribe((user) => {
      expect(user).toEqual(mockUser);
    });

    const req = httpTestingController.expectOne(`${service.apiUrl}/register`);
    expect(req.request.method).toEqual('POST');
    req.flush(mockUser);
  });

  it('should sign in a user', () => {
    const mockAuthenticationRequest: AuthenticationRequest = {
      username: 'testuser',
      password: 'password123',
    };

    const mockToken = 'mockToken';

    service.signInUser(mockAuthenticationRequest).subscribe((token) => {
      expect(token).toEqual(mockToken);
    });

    const req = httpTestingController.expectOne(`${service.apiUrl}/login`);
    expect(req.request.method).toEqual('POST');
    req.flush(mockToken);
  });

  it('should logout a user', () => {
    const mockJwt = 'mockJwt';

    service.logoutUser(mockJwt).subscribe((response) => {
      expect(response).toBeTruthy();
    });

    const req = httpTestingController.expectOne(`${service.apiUrl}/logout`);
    expect(req.request.method).toEqual('POST');
    req.flush('', { status: 200, statusText: 'OK' });
  });
});
