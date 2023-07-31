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
    let fetchedUserId: string

    return this.authService.userId
      .pipe(
        take(1),
        switchMap(userId => {
          fetchedUserId = userId as string
          return this.authService.token
        }),
        switchMap(token => {
          if (!fetchedUserId) {
            throw new Error('User not found')
          }
          const url = environment.baseUrl + `bookings.json?orderBy="userId"&&equalTo="${fetchedUserId}"&&auth=${token}`
          console.log('url : ', url)
          return this.http.get<{ [key: string]: bookingData }>(url)
        }),
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

    let generatedId = ''
    let fetchedUserId
    let newBooking: Booking

    return this.authService.userId.pipe(
      take(1),
      switchMap(userId => {
        fetchedUserId = userId
        return this.authService.token
      }),
      switchMap(token => {
        if (!fetchedUserId) {
          throw new Error('User not found!!');
        }
        newBooking = new Booking(
          Math.random().toString(),
          placeId,
          fetchedUserId,
          placeTitle,
          placeImg,
          firstName,
          lastName,
          guestNumber,
          startDate,
          endDate
        );
        return this.http.post<{ name: string }>(environment.baseUrl + `bookings.json?auth=${token}`,
          { ...newBooking, id: null }
        );
      }),
      switchMap(resData => {
        generatedId = resData.name;
        return this.bookings;
      }),
      take(1),
      tap(bookings => {
        newBooking.id = generatedId;
        this._bookings.next(bookings.concat(newBooking));
      })
    )
  }

  cancelBooking(bookingId: string) {
    //const url = 

    return this.authService.token.pipe(take(1), switchMap(token => {
      return this.http.delete(environment.baseUrl + `bookings/${bookingId}.json?auth=${token}`)
    }),
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
