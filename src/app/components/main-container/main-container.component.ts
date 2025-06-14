import { Component } from '@angular/core';
import { NavBarComponent } from "../nav-bar/nav-bar.component";
import { RouterModule } from '@angular/router';
import { ExpiredSessionComponent } from "../expired-session/expired-session.component";

@Component({
  selector: 'app-main-container',
  imports: [NavBarComponent, RouterModule],
  templateUrl: './main-container.component.html',
  styleUrl: './main-container.component.css'
})
export class MainContainerComponent {

}
