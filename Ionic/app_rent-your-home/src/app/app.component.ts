import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { Router } from '@angular/router';
import { Platform } from '@ionic/angular';
import { Capacitor } from '@capacitor/core';
import { SplashScreen } from '@capacitor/splash-screen';
import { Subscription, take } from 'rxjs';
import { App, AppState } from '@capacitor/app';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {

  private authSub!: Subscription
  private prevAuthState = false
  constructor(private auhthService: AuthService, private router: Router
    , private platform: Platform) {
    this.initializeApp()
  }

  initializeApp() {
    this.platform.ready().then(() => {
      if (Capacitor.isPluginAvailable('SplashScreen')) {
        SplashScreen.hide()
      }
    })
  }

  ngOnInit(): void {

    App.addListener('appStateChange', (isActive) => {
      console.log('appStateChange : ',isActive)
      this.checkAuthOnResume.bind(isActive)
    })

    this.authSub = this.auhthService.isUserAuthenticated.subscribe(isAuth => {
      if (!isAuth && this.prevAuthState !== isAuth) {
        this.router.navigateByUrl('/auth')
      }
      this.prevAuthState = isAuth
    })
  }
  
  ngOnDestroy(): void {
    if (this.authSub) {
      this.authSub.unsubscribe()
    }
  }

  onLogOut() {
    this.auhthService.logOut()
  }

  private checkAuthOnResume(state: AppState) {
    if (state.isActive) {
      this.auhthService.autoLogIn().pipe(take(1)).subscribe(success => {
        if (!success) {
          this.onLogOut()
        }
      })
    }
  }
}
