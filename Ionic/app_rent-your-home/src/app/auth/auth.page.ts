import { Component, OnInit } from '@angular/core';
import { AuthResData, AuthService } from './auth.service';
import { Router } from '@angular/router';
import { AlertController, LoadingController } from '@ionic/angular';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.page.html',
  styleUrls: ['./auth.page.scss'],
})
export class AuthPage implements OnInit {

  isLoading = false
  isLogIn = true
  constructor(private authService: AuthService, private router: Router,
    private loadingCtrl: LoadingController, private alertCtrl: AlertController) { }

  ngOnInit() {
  }


  onSwitchAuthMode() {
    this.isLogIn = !this.isLogIn
  }

  onSubmit(form: NgForm) {
    if (!form.valid) return

    const email = form.value.email
    const pwd = form.value.password
    this.authenticate(email, pwd)
    form.reset()
  }

  authenticate(email: string, password: string) {

    const observer = {
      next: (resData) => {
        console.log('Authentication Success : ' + resData)
        this.isLoading = false
        this.loadingCtrl.dismiss()
        this.router.navigateByUrl('/places/tabs/discover')
      },
      error: (errorRes) => {
        this.isLoading = false
        this.loadingCtrl.dismiss()
        const code = errorRes.error.error.message
        let msg = 'SignUp failed!! Try again later'
        if (code == 'EMAIL_EXISTS') {
          msg = 'Email already exists!!'
        }
        else if (code == 'EMAIL_NOT_FOUND') {
          msg = 'Email not found!!'
        }
        else if (code == 'INVALID_PASSWORD') {
          msg = 'Invalid Password!!'
        }
        this.showAlert(msg)
      },
      complete: () => {
      },
    }

    this.isLoading = true
    this.loadingCtrl.create(
      {
        keyboardClose: true,
        message: this.isLogIn? 'Logging In...':'Signing Up...'
      }
    )
      .then(loadingEl => {
        loadingEl.present()

        let authObs: Observable<AuthResData>
        if (this.isLogIn) {
          authObs = this.authService.logIn(email, password)
        } else {
          authObs = this.authService.signUp(email, password)
        }

        authObs.subscribe(observer)

      })
  }

  showAlert(msg: string) {
    this.alertCtrl.create({
      header: 'Authentication failed',
      message: msg,
      buttons: [{ text: 'OK' }]
    }).then(alertEl => {
      alertEl.present()
    })
  }

}
