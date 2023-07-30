import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { Capacitor } from '@capacitor/core';
import { AlertController, Platform } from '@ionic/angular';

@Component({
  selector: 'app-image-picker',
  templateUrl: './image-picker.component.html',
  styleUrls: ['./image-picker.component.scss'],
})
export class ImagePickerComponent implements OnInit {

  @ViewChild('filePicker') filePicker!: ElementRef<HTMLInputElement>
  selectednImg!: string
  usePicker = false

  constructor(private alertCtrl: AlertController, private platform: Platform) { }

  @Output() imagePick = new EventEmitter<string | File>()
  @Input() showPreview=false

  ngOnInit() {
    this.initialize()
  }

  initialize() {
    let isMobile = this.platform.is('mobile')
    let isHybrid = this.platform.is('hybrid')
    let isAndroid = this.platform.is('android')
    let isIOS = this.platform.is('ios')
    let isDesktop = this.platform.is('desktop')

    // console.log('Mobile : ', isMobile)
    // console.log('Hybrid : ', isHybrid)
    // console.log('Android : ', isAndroid)
    // console.log('iOS : ', isIOS)
    // console.log('Desktop : ', isDesktop)

    if (isMobile && !isHybrid || isDesktop) {
      this.usePicker = true
    }

    //console.log('usePicker : ', this.usePicker)
  }


  async onPickImage() {
    const isCameraAvailable = Capacitor.isPluginAvailable('Camera')
    console.log('isCameraAvailable : ', isCameraAvailable)
    if (!isCameraAvailable) {
      this.filePicker.nativeElement.click()
      return
    }

    const img = await Camera.getPhoto({
      quality: 100,
      source: CameraSource.Prompt,
      correctOrientation: true,
      height: 320,
      width: 200,
      resultType: CameraResultType.DataUrl
    })
      .then(image => {
        //console.log('image : ', image)
        this.selectednImg = image.dataUrl as string
        //console.log('selectednImg : ', this.selectednImg)
        this.imagePick.emit(this.selectednImg)
      })
      .catch(error => {
        console.log(error)
        if (this.usePicker) {
          this.filePicker.nativeElement.click()
        }
        // if (error)
        //   this.showAlert('Message', error.message)
        // else
        //   this.showAlert('Message', 'Camera not available')
        return false
      })
  }

  onFileChosen(event) {
    const target = event.target as HTMLInputElement
    if (target && target.files) {
      const pickedFile = target.files[0]
      if (!pickedFile) {
        return;
      }
      const fr = new FileReader()
      fr.readAsDataURL(pickedFile)
      fr.onload = () => {
        this.selectednImg = fr.result?.toString() as string
        this.imagePick.emit(pickedFile)
      }
    }
  }

  private showAlert(title, msg) {
    this.alertCtrl.create({
      header: title,
      message: msg,
      buttons: [{
        text: 'OK',
        role: 'cancel'
      }]
    }).then(alertEl => { alertEl.present() })
  }

}
