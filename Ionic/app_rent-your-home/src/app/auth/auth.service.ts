import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _isUserAuthenticated = true
  private _userId='xyz'
  constructor() { }

  get userId(){
    return this._userId
  }

  get isUserAuthenticated() {
    return this._isUserAuthenticated
  }

  logIn() {
    this._isUserAuthenticated = true
  }

  logOut() {
    this._isUserAuthenticated = false
  }
}
