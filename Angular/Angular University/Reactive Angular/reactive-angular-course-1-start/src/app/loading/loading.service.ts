import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, of } from "rxjs";
import { concatMap, finalize, tap } from "rxjs/operators";

@Injectable()
export class LoadingService {

    private loadingSubject = new BehaviorSubject<boolean>(false)
    loading$: Observable<boolean> = this.loadingSubject.asObservable()

    showLoaderUntillCompleted<T>(obs$: Observable<T>): Observable<T> {
        return of(null)
            .pipe(
                tap(() => this.showLoading(true)),
                concatMap(() => obs$),
                finalize(() => this.showLoading(false))
            )

    }

    showLoading(show: boolean) {
        this.loadingSubject.next(show)
    }
}