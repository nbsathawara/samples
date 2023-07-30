import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from './user.modal';

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
export class AuthService {

  private _isUserAuthenticated = false

  private _user = new BehaviorSubject<User | null>(null)

  constructor(private http: HttpClient) { }

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
        return ''
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
    const expireTime = new Date().getTime() + (+resData.expiresIn * 1000)
    this._user.next(new User(resData.localId, resData.email, resData.idToken, new Date(expireTime)))

  }

  logOut() {
    this._user.next(null)
  }
}
