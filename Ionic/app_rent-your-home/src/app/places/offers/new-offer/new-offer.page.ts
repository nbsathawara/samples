import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PlacesService } from '../../places.service';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';

@Component({
  selector: 'app-new-offer',
  templateUrl: './new-offer.page.html',
  styleUrls: ['./new-offer.page.scss'],
})
export class NewOfferPage implements OnInit {

  form!: FormGroup
  curDateStr = new Date().toISOString()

  constructor(private placeService: PlacesService, private router: Router,
    private loadingCtrl:LoadingController) { }

  ngOnInit() {
    this.form = new FormGroup({
      title: new FormControl(null, {
        updateOn: 'blur',
        validators: [Validators.required]
      }),
      description: new FormControl(null, {
        updateOn: 'blur',
        validators: [Validators.required, Validators.maxLength(180)]
      }
      ),
      price: new FormControl(null, {
        updateOn: 'blur',
        validators: [Validators.required, Validators.min(1)]
      }),
      fromDate: new FormControl(new Date().toISOString(), {
        updateOn: 'blur',
        validators: [Validators.required]
      })
      ,
      toDate: new FormControl(new Date().toISOString(), {
        updateOn: 'blur',
        validators: [Validators.required]
      })
    })
  }

  isValidDates() {
    const startDate = new Date(this.form.value.fromDate)
    const endDate = new Date(this.form.value.toDate)
    return endDate >= startDate
  }

  onCreateOffer() {
    if (!this.form.valid || !this.isValidDates())
      return

      this.loadingCtrl.create({
        message:"Creating Offer...",
      }).then(loadingEl=>{
        loadingEl.present()
      })
    this.placeService.addPlace(this.form.value.title,
      this.form.value.description, +this.form.value.price, new Date(this.form.value.fromDate)
      , new Date(this.form.value.toDate)).subscribe(()=>{
        this.loadingCtrl.dismiss()
        this.form.reset()
        this.router.navigate(['/places/tabs/offers'])
      })

  }

}
