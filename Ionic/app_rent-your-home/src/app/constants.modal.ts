import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
})

export class Constants {
    private baseUrl = 'https://ionic-sample-backend-default-rtdb.firebaseio.com/'
    getBaseUrl() {
        return [...this.baseUrl]
    }
}