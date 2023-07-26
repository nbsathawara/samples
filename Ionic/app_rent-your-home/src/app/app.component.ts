import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  constructor(private auhthService:AuthService,private router:Router) {}

  onLogOut(){
    this.auhthService.logOut()
    this.router.navigateByUrl('/auth')
  }
}
