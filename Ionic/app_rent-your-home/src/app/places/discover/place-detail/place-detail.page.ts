import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ActionSheetController, AlertController, LoadingController, ModalController, NavController } from '@ionic/angular';
import { PlacesService } from '../../places.service';
import { Place } from '../../place.model';
import { CreateBookingComponent } from '../../../bookings/create-booking/create-booking.component';
import { Observable, Subscription, switchMap, take } from 'rxjs';
import { BookingService } from 'src/app/bookings/booking.service';
import { AuthService } from 'src/app/auth/auth.service';
import { MapModalComponent } from 'src/app/shared/map-modal/map-modal.component';

@Component({
  selector: 'app-place-detail',
  templateUrl: './place-detail.page.html',
  styleUrls: ['./place-detail.page.scss'],
})
export class PlaceDetailPage implements OnInit, OnDestroy {

  place!: Place
  placeSubscription!: Subscription
  isBookable = false
  isLoading = false

  constructor(private navCtrl: NavController, private route: ActivatedRoute, private bookingService: BookingService
    , private placeService: PlacesService, private modalCtrl: ModalController, private authService: AuthService,
    private actionSheetCtrl: ActionSheetController, private loadingCtrl: LoadingController
    , private alertCtrl: AlertController, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('placeId')) {
        this.closeScreen()
        return
      }

      this.isLoading = true
      let fetchedUserId: string

      const placObserver = {
        next: (place) => {
          this.place = place as Place
          this.isBookable = this.place.userId !== fetchedUserId
          this.isLoading = false
        },
        error: (error) => {
          this.alertCtrl.create({
            message: "Something went wrong.Try again later.",
            header: 'Error',
            buttons: [
              {
                text: 'OK',
                handler: () => {
                  this.router.navigate(['/places/tabs/offers'])
                }
              }
            ]
          }).then(alertEl => {
            alertEl.present()
          })
        },
        complete: () => { }
      }

      this.authService.userId.pipe(switchMap(userId => {
        if (!userId) {
          throw new Error('User not found!!')
        }
        fetchedUserId = userId
        return this.placeService.getPlace(paramMap.get('placeId') as string)
      })).subscribe(placObserver)
    })
  }

  ngOnDestroy(): void {
    this.placeSubscription?.unsubscribe()
  }

  closeScreen() {
    this.navCtrl.navigateBack('/places/tabs/discover')
  }

  onBookPlace() {

    this.actionSheetCtrl.create({
      header: 'Choose an Action!!',
      buttons: [
        {
          text: 'Select Date',
          handler: () => {
            this.openBookingModal('select')
          }
        },
        {
          text: 'Random Date',
          handler: () => {
            this.openBookingModal('random')
          }
        },
        {
          text: 'Cancel',
          role: 'cancel'
        }
      ]
    }).then(actionSheetEl => {
      actionSheetEl.present()
    })
  }

  openBookingModal(mode: 'select' | 'random') {
    console.log(mode)

    this.modalCtrl.create({
      component: CreateBookingComponent
      , componentProps: { place: this.place, selectedMode: mode }
    })
      .then(modalEl => {
        modalEl.present()
        return modalEl.onDidDismiss()
      })
      .then(resultData => {
        if (resultData.role === 'booking-confirm') {
          this.loadingCtrl.create({
            message: 'Booking Place...'
          }).then(loadingEl => {
            loadingEl.present()
            const data = resultData.data.bookingData
            this.bookingService.addBooking(this.place.id, this.place.title, this.place.imgUrl
              , data.firstName, data.lastName, data.guestNumber, data.startDate, data.endDate)
              .subscribe(() => {
                loadingEl.dismiss()
                this.closeScreen()
              })
          })
        }
      })
  }

  onMapClicked() {
    this.modalCtrl.create({
      component: MapModalComponent, componentProps: {
        center: { lat: this.place.location.lat, lng: this.place.location.lng },
        selectable: false,
        closeBtnText: 'Close',
        title: this.place.location.address
      }
    })
      .then(modalEl => {
        modalEl.present()
      })
  }
}
