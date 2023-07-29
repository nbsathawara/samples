import { AfterViewInit, Component, ElementRef, Input, OnDestroy, OnInit, Renderer2, ViewChild } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-map-modal',
  templateUrl: './map-modal.component.html',
  styleUrls: ['./map-modal.component.scss'],
})
export class MapModalComponent implements OnInit, AfterViewInit, OnDestroy {

  @ViewChild('map') mapElRef!: ElementRef

  googleMaps: any
  mapClickListener: any

  @Input() center = environment.defGoogleMapCenter
  @Input() selectable = true
  @Input() closeBtnText = 'Cancel'
  @Input() title = 'Pick Location'

  constructor(private modalCtrl: ModalController, private renderer: Renderer2) { }

  ngOnInit() { }

  ngOnDestroy() {
    if (this.mapClickListener)
      this.googleMaps.eventRemoveListener(this.mapClickListener)
  }


  ngAfterViewInit(): void {

    this.getGoogleMaps()
      .then(googleMaps => {

        this.googleMaps = googleMaps
        const mapEl = this.mapElRef.nativeElement

        const map = new googleMaps.Map(mapEl, {
          center: this.center,
          zoom: 15
        })

        googleMaps.event.addListenerOnce(map, 'idle', () => {
          this.renderer.addClass(mapEl, 'visible')
        })

        if (this.selectable) {
          this.mapClickListener = map.addListener('click', event => {
            const cords = { lat: event.latLng.lat(), lng: event.latLng.lng() }
            this.modalCtrl.dismiss(cords)
          })
        } else {
          const marker = new googleMaps.Marker({
            position: this.center,
            map: map,
            title: 'Picked Location'
          })
          marker.setMap(map)
        }
      })
      .catch(error => {
        console.log(error)
      })


  }

  private getGoogleMaps(): Promise<any> {
    const win = window as any
    const googleModule = win.gooogle

    if (googleModule && googleModule.maps) {
      return Promise.resolve(googleModule.maps)
    }

    return new Promise((resolve, reject) => {
      const script = document.createElement('script')
      script.src =
        'https://maps.googleapis.com/maps/api/js?key=' + environment.googleMapsAPIKey;
      script.async = true
      script.defer = true
      document.body.appendChild(script)
      script.onload = () => {
        const loadedGoogleModule = win.google
        if (loadedGoogleModule && loadedGoogleModule.maps) {
          resolve(loadedGoogleModule.maps)
        } else {
          reject('Google Maps SDK not available.')
        }
      }
    })
  }

  onCancel() {
    this.modalCtrl.dismiss()
  }
}
