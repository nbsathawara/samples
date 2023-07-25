import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavController } from '@ionic/angular';
import { PlacesService } from '../../places.service';
import { Place } from '../../place.model';

@Component({
  selector: 'app-place-detail',
  templateUrl: './place-detail.page.html',
  styleUrls: ['./place-detail.page.scss'],
})
export class PlaceDetailPage implements OnInit {

  place!:Place
  constructor(private navCtrl: NavController,private route:ActivatedRoute,private placeService:PlacesService) { }

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
    this.navCtrl.navigateBack(['/places/tabs/discover']) 
  }
}
