import { Injectable } from '@angular/core';
import { Booking } from './booking.model';
import { BehaviorSubject, delay, map, switchMap, take, tap } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

interface bookingData {
  endDate: string
  firstName: string
  lastName: string
  guestNumber: number
  placeId: string
  placeImg: string
  placeTitle: string
  startDate: string
  userId: string
}
@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private _bookings = new BehaviorSubject<Booking[]>([])

  constructor(private authService: AuthService, private http: HttpClient) { }

  get bookings() {
    return this._bookings.asObservable()
  }

  fetchBookings() {
    const url = environment.baseUrl + `bookings.json?orderBy="userId"&&equalTo="${this.authService.userId}"`
    return this.http.get<{ [key: string]: bookingData }>(url)
      .pipe(
        map(
          bookingData => {
            const bookings: Booking[] = []
            for (const key in bookingData) {
              if (bookingData.hasOwnProperty(key)) {
                const data = bookingData[key]
                bookings.push(new Booking(key, data.placeId, data.userId, data.placeTitle, data.placeImg
                  , data.firstName, data.lastName, data.guestNumber, new Date(data.startDate), new Date(data.endDate)))
              }
            }
            return bookings
          }
        ), tap(bookings => {
          this._bookings.next(bookings)
        }))
  }

  addBooking(placeId: string, placeTitle: string, placeImg: string,
    firstName: string, lastName: string, guestNumber: number,
    startDate: Date, endDate: Date) {

    let generateId = ''
    const newBooking = new Booking(Math.random().toString(), placeId, this.authService.userId,
      placeTitle, placeImg
      , firstName, lastName,
      guestNumber, startDate, endDate)

    return this.http.post<{ name: string }>(environment.baseUrl + 'bookings.json',
      {
        ...newBooking, id: null
      }
    )
      .pipe
      (switchMap(resData => {
        generateId = resData.name
        return this.bookings
      }
      ), take(1), tap(bookings => {
        newBooking.id = generateId
        this._bookings.next(bookings.concat(newBooking))
      }
      ))
  }

  cancelBooking(bookingId: string) {
    const url = environment.baseUrl + `bookings/${bookingId}.json`

    return this.http.delete(url)
      .pipe(
        switchMap(() => {
          return this.bookings
        }),
        take(1),
        tap(bookings => {
          this._bookings.next(
            bookings.filter(
              booking => {
                return booking.id != bookingId
              }))
        })

      )

  }
}
