import { Component, OnDestroy, OnInit } from '@angular/core';
import { Place } from '../../place.model';
import { ActivatedRoute } from '@angular/router';
import { NavController } from '@ionic/angular';
import { PlacesService } from '../../places.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-offer-bookings',
  templateUrl: './offer-bookings.page.html',
  styleUrls: ['./offer-bookings.page.scss'],
})
export class OfferBookingsPage implements OnInit, OnDestroy {

  place!: Place
  placeSubscription!: Subscription
  constructor(private route: ActivatedRoute, private navCtrl: NavController, private placeService: PlacesService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('placeId')) {
        this.closeScreen()
        return
      }
      this.placeService.getPlace(paramMap.get('placeId') as string)
        .subscribe(place => {
          this.place = place as Place
        })
    })
  }

  ngOnDestroy(): void {
    this.placeSubscription?.unsubscribe()
  }

  closeScreen() {
    this.navCtrl.navigateBack('/places/tabs/offers')
  }

}
