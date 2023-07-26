import { Injectable } from '@angular/core';
import { Booking } from './booking.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private _bookings: Booking[] =
    [{
      id: 'xyz', placeId: 'p1', placeTitle: 'Manhattan Mansion', gurstNumber: 2, userId: 'xyz'
    }]

  constructor() { }

  get bookings() {
    return [...this._bookings]
  }
}
