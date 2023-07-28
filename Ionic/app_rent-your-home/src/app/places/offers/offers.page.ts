import { Component, OnDestroy, OnInit } from '@angular/core';
import { Place } from '../place.model';
import { PlacesService } from '../places.service';
import { IonItemSliding } from '@ionic/angular';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.page.html',
  styleUrls: ['./offers.page.scss'],
})
export class OffersPage implements OnInit, OnDestroy {
  offers!: Place[]
  placesSubsctiption!: Subscription

  constructor(private placeService: PlacesService, private router:
    Router) { }

  ngOnInit() {
    this.placesSubsctiption = this.placeService.places.subscribe(places => {
      this.offers = places
    })
  }

  ngOnDestroy(): void {
    this.placesSubsctiption?.unsubscribe()
  }

  onEdit(offerId: string, slidingItem: IonItemSliding) {
    this.router.navigate(['/', 'places', 'tabs', 'offers', 'edit', offerId])
    slidingItem.close()
  }

}
