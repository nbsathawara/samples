import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ActionSheetController, ModalController, NavController } from '@ionic/angular';
import { PlacesService } from '../../places.service';
import { Place } from '../../place.model';
import { CreateBookingComponent } from '../../../bookings/create-booking/create-booking.component';

@Component({
  selector: 'app-place-detail',
  templateUrl: './place-detail.page.html',
  styleUrls: ['./place-detail.page.scss'],
})
export class PlaceDetailPage implements OnInit {

  place!: Place
  constructor(private navCtrl: NavController, private route: ActivatedRoute
    , private placeService: PlacesService, private modalCtrl: ModalController,
    private actionSheetCtrl: ActionSheetController) { }

  ngOnInit() {
    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('placeId')) {
        this.closeScreen()
        return
      }
      this.place = this.placeService.getPlace(paramMap.get('placeId') as string) as Place
    })
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
      , componentProps: { place: this.place ,selectedMode:mode}
    })
      .then(modalEl => {
        modalEl.present()
        return modalEl.onDidDismiss()
      })
      .then(resultData => {
        console.log(resultData)
      })
  }
}
