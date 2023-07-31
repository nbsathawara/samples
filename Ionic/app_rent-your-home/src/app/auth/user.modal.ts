export class User {
    constructor(public id: string, public email: string, private _token: String, private _tokenExpDate: Date) { }

    get token() {
        if (!this._tokenExpDate || this._tokenExpDate <= new Date())
            return null
        else return this._token
    }

    get userId() {
        return this.id
    }

    get toeknDuration() {
        if (!this.token) {
            return 0
        }
        //return 5000
        return this._tokenExpDate.getTime() - new Date().getTime()
    }
}