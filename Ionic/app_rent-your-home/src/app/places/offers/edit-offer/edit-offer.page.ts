import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PlacesService } from '../../places.service';
import { LoadingController, NavController } from '@ionic/angular';
import { Place } from '../../place.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-edit-offer',
  templateUrl: './edit-offer.page.html',
  styleUrls: ['./edit-offer.page.scss'],
})
export class EditOfferPage implements OnInit, OnDestroy {

  offer!: Place
  form!: FormGroup
  offerSubscription!: Subscription
  constructor(private route: ActivatedRoute, private navCtrl: NavController
    , private placeService: PlacesService, private router: Router, private loadintCrl: LoadingController) { }

  ngOnInit() {

    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('placeId')) {
        this.closeScreen()
        return
      }
      this.placeService.getPlace(paramMap.get('placeId') as string)
        .subscribe(offer => {
          this.offer = offer as Place
        })

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

  ngOnDestroy(): void {
    this.offerSubscription?.unsubscribe()
  }

  onUpdateOffer() {
    if (!this.form.valid)
      return
    this.loadintCrl.create({
      message: 'Updating offer...'
    }).then(loadingEl => {
      loadingEl.present()
      this.placeService.updatePlace(this.offer.id, this.form.value.title, this.form.value.desc)
        .subscribe(() => {
          loadingEl.dismiss()
          this.form.reset()
          this.router.navigate(['/places/tabs/offers'])
        })
    })
  }

  closeScreen() {
    this.navCtrl.navigateBack('/places/tabs/offers')
  }

}
