import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { PersonsService } from "./persons.service";

@Component({
    selector: 'app-person-input',
    templateUrl: './person-input.component.html',
    styleUrls: ['./person-input.component.css']

})
export class PersonInputComponent implements OnInit {
    
    enteredName=''

    constructor (private pService:PersonsService){

    }

    ngOnInit(): void {
         
    }

    onCreatePerson() { 
        this.pService.addPerson(this.enteredName)
        this.enteredName=''
    }
}