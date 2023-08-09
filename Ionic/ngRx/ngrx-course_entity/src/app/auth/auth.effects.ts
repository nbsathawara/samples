import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { json } from "body-parser";
import { AuthActions } from "./action-types";
import { tap } from "rxjs/operators";
import { Router } from "@angular/router";

@Injectable()
export class AuthEffects {

    logIn$ = createEffect(() =>
        this.actions$.pipe(ofType(AuthActions.logIn),
            tap(action =>
                localStorage.setItem('user'
                    , JSON.stringify(action.user))
            ))
        , { dispatch: false })


    logOut$ = createEffect(() =>
        this.actions$.pipe(ofType(AuthActions.logOut)
            , tap(action => {
                localStorage.removeItem('user')
                this.router.navigateByUrl('/login')
            }
            ))
        , { dispatch: false })

    constructor(private actions$: Actions, private router: Router) {


    }

}