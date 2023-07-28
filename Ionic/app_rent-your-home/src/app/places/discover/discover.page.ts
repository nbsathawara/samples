import { Component, OnDestroy, OnInit } from '@angular/core';
import { PlacesService } from '../places.service';
import { Place } from '../place.model';
import { InfiniteScrollCustomEvent, IonInput, SegmentChangeEventDetail, SegmentCustomEvent } from '@ionic/angular';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-discover',
  templateUrl: './discover.page.html',
  styleUrls: ['./discover.page.scss'],
})
export class DiscoverPage implements OnInit, OnDestroy {

  _places!: Place[]
  _filteredPlaces!: Place[]
  placesSubscription!: Subscription
  isLoading=false

  constructor(private authService: AuthService, private placeService: PlacesService) { }

  ngOnInit() {
    this.placesSubscription = this.placeService.places.subscribe(places => {
      this._places = places
      this._filteredPlaces = this._places
    })
  }

  ionViewWillEnter(){
    this.isLoading = true;
    this.placeService.fetchPlaces().subscribe(() => {
      this.isLoading = false
    }
    )
  }

  ngOnDestroy(): void {
    this.placesSubscription?.unsubscribe()
  }

  onSegmentUpdate(event, detail?: CustomEvent) {
    const filter = (event as SegmentCustomEvent).detail.value
    if (filter === 'all') {
      this._filteredPlaces = this._places
    } else {
      this._filteredPlaces = this._places.filter(place => { 
        return place.userId != this.authService.userId
      })
    } 
  }
}
