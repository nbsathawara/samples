import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PlacesService } from '../../places.service';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { PlaceLocation } from '../../location.model';
import { switchMap } from 'rxjs';

function base64toBlob(base64Data, contentType) {
  contentType = contentType || '';
  const sliceSize = 1024;
  const byteCharacters = atob(base64Data);
  const bytesLength = byteCharacters.length;
  const slicesCount = Math.ceil(bytesLength / sliceSize);
  const byteArrays = new Array(slicesCount);

  for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
    const begin = sliceIndex * sliceSize;
    const end = Math.min(begin + sliceSize, bytesLength);

    const bytes = new Array(end - begin);
    for (let offset = begin, i = 0; offset < end; ++i, ++offset) {
      bytes[i] = byteCharacters[offset].charCodeAt(0);
    }
    byteArrays[sliceIndex] = new Uint8Array(bytes);
  }
  return new Blob(byteArrays, { type: contentType });
}

@Component({
  selector: 'app-new-offer',
  templateUrl: './new-offer.page.html',
  styleUrls: ['./new-offer.page.scss'],
})
export class NewOfferPage implements OnInit {

  form!: FormGroup
  curDateStr = new Date().toISOString()

  constructor(private placeService: PlacesService, private router: Router,
    private loadingCtrl: LoadingController) { }

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
      }),
      location: new FormControl(null, {
        validators: [Validators.required]
      }),
      image: new FormControl(null)
    })
  }

  onLoactionPicked(location: PlaceLocation) {
    this.form.patchValue({ location: location })
  }

  onImagePicked(imgData: string | File) {
    let imgFile
    if (typeof imgData === 'string') {
      try {
        imgFile = base64toBlob(imgData.replace('data:image/jpeg;base64,', ''), 'image/jpeg')
      } catch (error) {
        console.log('Error in onImagePicked : ', (error as DOMException).name, (error as DOMException).message)
        return
      }
    } else {
      imgFile = imgData
    }
    this.form.patchValue({ image: imgFile })
  }

  isValidDates() {
    const startDate = new Date(this.form.value.fromDate)
    const endDate = new Date(this.form.value.toDate)
    return endDate >= startDate
  }

  onCreateOffer() {
    console.log(this.form)
    if (!this.form.valid || !this.isValidDates()
      || !this.form.get('image')?.value)
      return
    this.loadingCtrl.create({
      message: "Creating Offer...",
    }).then(loadingEl => {
      loadingEl.present()
      this.placeService.addPlace(this.form.value.title,
        this.form.value.description, +this.form.value.price, new Date(this.form.value.fromDate)
        , new Date(this.form.value.toDate), this.form.value.location,
        this.placeService.uploadImage(this.form.get('image')?.value))
        // this.placeService.uploadImage(this.form.get('image')!.value)
        //   .pipe(switchMap(uploadRes => {
        //     return this.placeService.addPlace(this.form.value.title,
        //       this.form.value.description, +this.form.value.price, new Date(this.form.value.fromDate)
        //       , new Date(this.form.value.toDate), this.form.value.location, uploadRes.imageUrl)
        //   }))
        .subscribe(() => {
          this.loadingCtrl.dismiss()
          this.form.reset()
          this.router.navigate(['/places/tabs/offers'])
        })
    })
  }

}
