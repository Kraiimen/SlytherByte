import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DataService } from '../../services/dataService';

@Component({
  selector: 'app-nav-bar',
  imports: [RouterLink],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  private _dataService = inject(DataService);
  loggedUserUsername: string | null = null ;
  isMenuOpen = false;

  constructor() {
    this.loggedUserUsername = this._dataService.loggedUser?.username || null;
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
