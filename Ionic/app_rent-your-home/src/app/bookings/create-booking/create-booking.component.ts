import { Component, Input, NgModule, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DatetimeChangeEventDetail, IonDatetime, ModalController } from '@ionic/angular';
import { Place } from 'src/app/places/place.model';

@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.scss'],
})

export class CreateBookingComponent implements OnInit {
  @Input() place!: Place

  form!: FormGroup
  toMinDate: string = new Date().toISOString()

  @Input() selectedMode!: 'select' | 'random'
  startDate!: string
  endDate!: string

  constructor(private modalCtrl: ModalController) { }

  ngOnInit() {

    if (this.selectedMode == 'random') {
      const availableFrom = new Date(this.place.availableFrom)
      const availableTo = new Date(this.place.availableTo)

      this.startDate = new Date(
        availableFrom.getTime()
        + Math.random()
        * (availableTo.getTime()
          - (7 * 24 * 60 * 60 * 1000)
          - availableFrom.getTime()))
        .toISOString()

      this.endDate = new Date(
        new Date(this.startDate).getTime()
        + Math.random()
        * (new Date(this.startDate).getTime()
          + (6 * 24 * 60 * 60 * 1000)
          - new Date(this.startDate).getTime())
      ).toISOString()

    }

    this.toMinDate = this.place.availableFrom.toISOString()

    this.form = new FormGroup({
      firstName: new FormControl(null, {
        updateOn: 'blur',
        validators: [Validators.required]
      }),
      lastName: new FormControl(null, {
        updateOn: 'blur',
        validators: [Validators.required]
      }),
      noOfGuests: new FormControl("2", {
        updateOn: 'blur',
        validators: [Validators.required]
      }),
      fromDate: new FormControl(this.startDate, {
        updateOn: 'blur',
        validators: [Validators.required]
      })
      ,
      toDate: new FormControl(this.endDate, {
        updateOn: 'blur',
        validators: [Validators.required]
      })
    })

  }

  onFromDateChange(event) {
    const fromDate = event?.detail?.value
    this.toMinDate = fromDate
  }

  isValidDates() {
    const fromDate = this.form.get('fromDate')?.value
    const toDate = this.form.get('toDate')?.value 
    return toDate >= fromDate
  }

  onCancel() {
    this.modalCtrl.dismiss(null, 'cancel')
  }

  onBookPlace() {
    if (!this.form.valid || !this.isValidDates())
      return
    console.log(this.form.value)
    this.modalCtrl.dismiss({
      bookingData: {
        firstName: this.form.get('firstName'),
        lastName: this.form.get('lastName'),
        noOfGuests: this.form.get('noOfGuests'),
        startDate: this.form.get('fromDate'),
        endDate: this.form.get('toDate')

      }
    }, 'booking-confirm')
  }

}
