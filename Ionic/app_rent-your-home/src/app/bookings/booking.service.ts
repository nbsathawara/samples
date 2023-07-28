import { Injectable } from '@angular/core';
import { Booking } from './booking.model';
import { BehaviorSubject, delay, take, tap } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private _bookings = new BehaviorSubject<Booking[]>([])

  constructor(private authService: AuthService) { }

  get bookings() {
    return this._bookings.asObservable()
  }

  addBooking(placeId: string, placeTitle: string, placeImg: string,
    firstName: string, lastName: string, guestNumber: number,
    startDate: Date, endDate: Date) {

    const newBooking = new Booking(Math.random().toString(), placeId, this.authService.userId,
      placeTitle, placeImg
      , firstName, lastName,
      guestNumber, startDate, endDate)

    const observer = {
      next: (bookings) => {
        this._bookings.next(bookings.concat(newBooking))
        console.log(this._bookings)
      },
      error: (error) => {
        console.log('error : ' + error)
      },
      complete: () => {
        console.log('completed.')
      },
    }

    return this.bookings.pipe(take(1), delay(2000), tap(observer))
  }

  cancelBooking(bookingId: string) {
    const observer = {
      next: (bookings) => {
        this._bookings.next(
          bookings.filter(
            booking => {
              return booking.id != bookingId
            }))
        console.log(this._bookings)
      },
      error: (error) => {
        console.log('error : ' + error)
      },
      complete: () => {
        console.log('completed.')
      },
    }
    return this.bookings.pipe(take(1), delay(2000), tap(observer))
  }
}
