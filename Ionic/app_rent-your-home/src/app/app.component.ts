import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { Router } from '@angular/router';
import { Platform } from '@ionic/angular';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  constructor(private auhthService:AuthService,private router:Router) {
   this.initializeApp()
  }

  initializeApp(){

  }

  onLogOut(){
    this.auhthService.logOut()
    this.router.navigateByUrl('/auth')
  }
}
