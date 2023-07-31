import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject, from, map, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './user.modal';
import { Capacitor } from '@capacitor/core';
import { Preferences } from '@capacitor/preferences';
import { JsonPipe } from '@angular/common';
import { AlertController } from '@ionic/angular';

export interface AuthResData {
  kind: string,
  idToken: string,
  email: string,
  refreshToken: string,
  expiresIn: string,
  localId: string,
  registered: boolean
}

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {

  private _user = new BehaviorSubject<User | null>(null)
  private activeLogOutTimer: any

  constructor(private http: HttpClient, private alertCtrl: AlertController) { }

  get isUserAuthenticated() {
    return this._user.asObservable().pipe(map(user => {
      if (user) {
        return !!user.token
      }
      else { return false }
    }))
  }

  get userId() {
    return this._user.asObservable().pipe(map(user => {
      if (user) {
        return user.id
      }
      else {
        return null
      }
    }))
  }

  get token() {
    return this._user.asObservable().pipe(map(user => {
      if (user) {
        return user.token
      }
      else {
        return null
      }
    }))
  }

  signUp(email: string, password: string) {
    console.log(email + ' : ' + password)
    return this.http
      .post<AuthResData>(`https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${environment.firebaseAPIKey}`,
        {
          email: email,
          password: password,
          returnSecureToken: true
        }).pipe(tap(this.createUser.bind(this)))
  }

  logIn(email: string, password: string) {
    return this.http
      .post<AuthResData>(`https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${environment.firebaseAPIKey}`,
        {
          email: email,
          password: password,
          returnSecureToken: true
        }).pipe(tap(this.createUser.bind(this)))
  }

  private createUser(resData: AuthResData) {
    const expireTime = new Date(new Date().getTime() + (+resData.expiresIn * 1000))
    const user = new User(resData.localId, resData.email, resData.idToken, expireTime)
    this._user.next(user)
    this.storeAuthData(resData.localId, resData.email, resData.idToken, expireTime.toISOString())
    this.autoLogOut(user.toeknDuration)
  }

  autoLogIn() {
    return from(Preferences.get({ key: 'authData' }))
      .pipe(map(storedData => {
        if (!storedData || !storedData.value) {
          return null
        }

        const parsedData = JSON.parse(storedData.value) as
          {
            userId: string,
            email: string,
            token: string,
            tokenExpDate: string
          }

        const tokenExpTime = new Date(parsedData.tokenExpDate)
        if (tokenExpTime <= new Date()) {
          return null
        }

        const user = new User(parsedData.userId, parsedData.email, parsedData.token, tokenExpTime)
        console.log(`user in authoLogIn : ${user}`)
        return user
      }),
        tap(user => {
          if (user) {
            this._user.next(user)
            this.autoLogOut(user.toeknDuration)
          }
        }), map(user => {
          return !!user
        })
      )
  }

  private storeAuthData(userId: string, email: string, token: string, tokenExpDate: string) {
    const data = JSON.stringify({ userId: userId, email: email, token: token, tokenExpDate: tokenExpDate })
    Preferences.set({ key: 'authData', value: data })
  }

  logOut() {
    if (this.activeLogOutTimer) {
      clearTimeout(this.activeLogOutTimer)
    }
    this._user.next(null)
    return Preferences.remove({ key: 'authData' })
  }

  private autoLogOut(duration: number) {
    if (this.activeLogOutTimer) {
      clearTimeout(this.activeLogOutTimer)
    }
    this.activeLogOutTimer = setTimeout(() => {
      this.logOut().then(() => {
        // this.alertCtrl.create({
        //   header: 'Message',
        //   message: 'Session Expired.',
        //   backdropDismiss: false,
        //   buttons: [{
        //     text: 'OK',
        //     role: 'cancel'
        //   }]
        // }).then(alertEl => {
        //   alertEl.present()
        // })
      })


    }, duration)
  }


  ngOnDestroy(): void {
    if (this.activeLogOutTimer) {
      clearTimeout(this.activeLogOutTimer)
    }
  }
}
