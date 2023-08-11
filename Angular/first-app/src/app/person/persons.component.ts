import { Component, OnDestroy, OnInit } from "@angular/core";
import { PersonsService } from "./persons.service";
import { Subscription } from "rxjs";


@Component({
    selector: 'app-person',
    templateUrl: './persons.component.html'
})
export class PersonComponent implements OnInit, OnDestroy {
    private psersonListSub!: Subscription
    personList!: string[]
    isLoading=false

    constructor(private pService: PersonsService) {

    }

    ngOnInit(): void { 
        this.psersonListSub = this.pService.personChange.subscribe(persons => {
            this.personList = persons
            this.isLoading=false;
        })
        this.pService.fetchPersons()
        this.isLoading=true;
    }

    ngOnDestroy(): void {
        this.psersonListSub.unsubscribe()
    }

    onReovePerson(name: string) {
        this.pService.removePerson(name)
    }
}