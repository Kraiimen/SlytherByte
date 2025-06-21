import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DataService } from '../../services/dataService';

@Component({
  selector: 'app-nav-bar',
  imports: [RouterLink],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit {
  private _dataService = inject(DataService);
  loggedUserUsername: string | undefined = undefined ;
  isMenuOpen = false;

  ngOnInit(): void {
    this._dataService.loggedUser$.subscribe({
      next: user => this.loggedUserUsername = user?.usernameAccount,
      error: err => console.log(err)
    });
  }

  logout() {
    this._dataService.clearData();
    localStorage.removeItem('token');
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
