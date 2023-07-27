import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlacesService } from '../../places.service';
import { NavController } from '@ionic/angular';
import { Place } from '../../place.model';
import { FormControl, FormGroup, Validators } from '@angular/forms'; 

@Component({
  selector: 'app-edit-offer',
  templateUrl: './edit-offer.page.html',
  styleUrls: ['./edit-offer.page.scss'],
})
export class EditOfferPage implements OnInit {

  offer!: Place
  form!: FormGroup
  constructor(private route: ActivatedRoute, private navCtrl: NavController, private placeService: PlacesService) { }

  ngOnInit() {

    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('placeId')) {
        this.closeScreen()
        return
      }
      this.offer = this.placeService.getPlace(paramMap.get('placeId') as string) as Place

      this.form = new FormGroup({
        title: new FormControl(this.offer.title, {
          updateOn: 'blur',
          validators: [Validators.required]
        }),
        description: new FormControl(this.offer.desc, {
          updateOn: 'blur',
          validators: [Validators.required, Validators.maxLength(180)]
        }
        ),
      })
    })
  }

  onUpdateOffer(){
if(!this.form.valid)
return
console.log(this.form)
  }

  closeScreen() {
    this.navCtrl.navigateBack('/places/tabs/offers')
  }

}
