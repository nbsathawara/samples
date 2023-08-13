import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { User } from "../model/user";
import { map, shareReplay, tap } from "rxjs/operators";
import { HttpClient } from "@angular/common/http";
import { LoadingService } from "../loading/loading.service";

const AUTH_DATA = 'auth_data'
@Injectable({
    providedIn: 'root'
})
export class AuthStore {

    private subject = new BehaviorSubject<User>(null)
    user$: Observable<User> = this.subject.asObservable()
    isLoggedIn$: Observable<boolean>
    isLoggedOut$: Observable<boolean>

    constructor(private http: HttpClient, private loading: LoadingService) {
        this.isLoggedIn$ = this.user$.pipe(
            map(user => !!user)
        )
        this.isLoggedOut$ = this.isLoggedIn$.pipe(map(loggedIn => !loggedIn));

        const user = localStorage.getItem(AUTH_DATA)
        if (user)
            this.subject.next(JSON.parse(user))
    }

    logIn(email: string, password: string): Observable<User> {

        return this.http.post<User>('api/login', { email, password })
            .pipe(
                tap(user => {
                    localStorage.setItem(AUTH_DATA, JSON.stringify(user))
                    this.subject.next(user)
                }
                ),
                shareReplay()
            )

    }

    logOut() {
        localStorage.removeItem(AUTH_DATA)
        this.subject.next(null)
    }
}