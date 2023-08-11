import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { map } from "rxjs/operators";


@Injectable({
    providedIn: 'root'
})
export class PersonsService {

    personChange = new Subject<string[]>()
    persons!: string[]

    constructor(private httpClinet: HttpClient) { }

    fetchPersons() {
        this.httpClinet.get<any>('https://swapi.dev/api/people')
            .pipe(map(response => {
                return response.results.map(c => c.name)
            }))
            .subscribe(response => {
               this.personChange.next(response)
            })
    }

    addPerson(name: string) {
        this.persons.push(name)
        this.personChange.next(this.persons)
    }

    removePerson(name: string) {
        this.persons = this.persons.filter(
            person => {
                return person != name
            }
        )
        this.personChange.next(this.persons)
    }
}