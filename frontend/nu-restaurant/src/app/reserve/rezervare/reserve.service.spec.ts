import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReserveService } from './reserve.service';
import { Reserve } from '../models/reserve.model';


describe('ReserveService', () => {
  let service: ReserveService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ReserveService]});
    service = TestBed.inject(ReserveService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should create a reservation', () => {
    const mockReservation: Reserve = {
      masa: 'Table1',
      name:'Vasile',
      phone:"0734567891",
      data: "2024-01-20",
      time: "12:30:00",
      time_end: "14:00:00",
      guests: 2,
      specialRequests: "Nimic"
    };

    service.createReserve(mockReservation).subscribe((reservation) => {
      expect(reservation).toEqual(mockReservation);
    });
    const req = httpTestingController.expectOne('http://localhost:8083/api/v1/rezervare/create');
    expect(req.request.method).toEqual('POST');
    req.flush(mockReservation);
  });
  it('should update a reservation', () => {
    const mockReservation: Reserve = {
      masa: 'Table1',
      name:'Vasile',
      phone:"0734567891",
      data: "2024-01-20",
      time: "12:30:00",
      time_end: "14:00:00",
      guests: 2,
      specialRequests: "Nimic"
    };

    service.updateReserve(mockReservation).subscribe((reservation) => {
      expect(reservation).toEqual(mockReservation);
    });
    const req = httpTestingController.expectOne('http://localhost:8083/api/v1/rezervare');
    expect(req.request.method).toEqual('PUT');
    req.flush(mockReservation);
  });

});
