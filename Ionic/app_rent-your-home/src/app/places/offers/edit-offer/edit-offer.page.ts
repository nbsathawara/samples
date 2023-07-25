import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlacesService } from '../../places.service';
import { NavController } from '@ionic/angular';
import { Place } from '../../place.model'; 

@Component({
  selector: 'app-edit-offer',
  templateUrl: './edit-offer.page.html',
  styleUrls: ['./edit-offer.page.scss'],
})
export class EditOfferPage implements OnInit {

  offer!: Place
  constructor(private route: ActivatedRoute, private navCtrl: NavController, private placeService: PlacesService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('placeId')) {
        this.closeScreen()
        return
      }
      this.offer = this.placeService.getPlace(paramMap.get('placeId') as string) as Place
    })
  }

  closeScreen() {
    this.navCtrl.navigateBack('/places/tabs/offers')
  }

}
