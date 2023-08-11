import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../model/message';
import { tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Component({
  selector: 'messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  showMsg = false
  errors$: Observable<string[]>

  constructor(public msgService: MessageService) {
    console.log('Create Msg Component...')
  }

  ngOnInit() {
    this.errors$ = this.msgService.errors$.pipe(
      tap(() => this.showMsg = true)
    )
  }


  onClose() {
    this.showMsg = false
  }

}
