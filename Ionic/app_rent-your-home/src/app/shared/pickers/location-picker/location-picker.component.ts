import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActionSheetController, AlertController, ModalController } from '@ionic/angular';
import { MapModalComponent } from '../../map-modal/map-modal.component';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map, of, switchMap } from 'rxjs';
import { Coordintates, PlaceLocation } from 'src/app/places/location.model';
import { Geolocation } from '@capacitor/geolocation';
import { Capacitor } from '@capacitor/core';

@Component({
  selector: 'app-location-picker',
  templateUrl: './location-picker.component.html',
  styleUrls: ['./location-picker.component.scss'],
})
export class LocationPickerComponent implements OnInit {

  pickedLocationImg!: string
  isLoading = false

  @Output() locationPick = new EventEmitter<PlaceLocation>()
  @Input() showPreview  = false


  constructor(private modalCtrl: ModalController, private http: HttpClient,
    private actionSheetCtrl: ActionSheetController, private alertCtrl: AlertController) { }

  ngOnInit() { }

  onPickLocation() {

    this.actionSheetCtrl.create({
      header: 'PleaseSelect',
      buttons: [
        {
          text: 'Auto Locate',
          handler: () => { this.locateUser() }
        },
        {
          text: 'Pick on Map',
          handler: () => { this.openMap() }
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

  private locateUser() {
    if (!Capacitor.isPluginAvailable('Geolocation')) {
      this.showAlert()
      return
    }
    this.isLoading = true;
    Geolocation.getCurrentPosition()
      .then(geoPosition => {
        const cords: Coordintates =
        {
          lat: geoPosition.coords.latitude,
          lng: geoPosition.coords.longitude
        }
        this.createPlace(cords.lat, cords.lng)
        this.isLoading = false
      })
      .catch(error => {
        this.isLoading = false
        this.showAlert()
      })
  }

  private openMap() {
    this.modalCtrl.create({
      component: MapModalComponent
    }).then(modalEl => {
      modalEl.present()
      modalEl.onDidDismiss().then(modalData => {
        if (!modalData.data) return

        this.createPlace(modalData.data.lat, modalData.data.lng)
      })
    })
  }

  private createPlace(lat: number, lng: number) {

    this.isLoading = true
    const pickedLocation: PlaceLocation = {
      lat: lat,
      lng: lng,
      address: '',
      staticMNapImgUrl: ''
    }
    this.getAddress(lat, lng)
      .pipe(switchMap(address => {
        pickedLocation.address = address
        return of(this.getPickedLocationMapImg(pickedLocation.lat, pickedLocation.lng, 14))
      })).subscribe(staticMapImgUrl => {

        pickedLocation.staticMNapImgUrl = staticMapImgUrl
        this.pickedLocationImg = staticMapImgUrl
        this.isLoading = false

        this.locationPick.emit(pickedLocation)

      })
  }

  private getAddress(lat: number, lng: number) {
    return this.http.
      get<any>(`https://maps.googleapis.com/maps/api/geocode/json?latlng=${lat},${lng}&key=${environment.googleMapsAPIKey}`)
      .pipe(map(geoData => {
        if (!geoData || !geoData.results || geoData.results.length === 0) {
          return null
        }
        return geoData.results[0].formatted_address
      }))
  }

  private getPickedLocationMapImg(lat: number, lng: number, zoom: number) {
    return `https://maps.googleapis.com/maps/api/staticmap?center=${lat},${lng}&zoom=${zoom}&size=600x300&maptype=roadmap
&markers=color:red%7Clabel:Place%7C${lat},${lng}
&key=${environment.googleMapsAPIKey}`
  }

  private showAlert() {
    this.alertCtrl.create({
      header: 'Message',
      message: 'Unable to fecth current location!! Please use Map to pick location',
      buttons: [{
        text: 'OK',
        role: 'cancel'
      }]
    }).then(alertEl => { alertEl.present() })
  }

}
