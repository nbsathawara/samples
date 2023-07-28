import { Component, OnDestroy, OnInit } from '@angular/core';
import { BookingService } from './booking.service';
import { Booking } from './booking.model';
import { IonItemSliding, LoadingController } from '@ionic/angular';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.page.html',
  styleUrls: ['./bookings.page.scss'],
})
export class BookingsPage implements OnInit, OnDestroy {

  bookings!: Booking[]
  bookingSubsription!: Subscription
  isLoading = false

  constructor(private bookingService: BookingService, private loadingCtrl: LoadingController) { }

  ngOnInit() {
    this.bookingSubsription = this.bookingService.bookings.subscribe(bookings => {
      this.bookings = bookings
    })
  }

  ionViewWillEnter() {
    this.isLoading = true
    this.bookingService.fetchBookings().subscribe(() => {
      this.isLoading = false
    })
  }

  ngOnDestroy(): void {
    this.bookingSubsription!.unsubscribe()
  }

  onDelete(bookingId: string, slidingItem: IonItemSliding) {

    this.loadingCtrl.create({
      message: 'Cancelling Booking...'
    }).then(loadingEl => {
      loadingEl.present()
      slidingItem.close()
      this.bookingService.cancelBooking(bookingId).subscribe(() => {
        loadingEl.dismiss()
      })
    })
  }

}
