import { Component, inject, OnInit } from '@angular/core';
import { UserGameService } from '../../../services/userGameService';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UserGame } from '../../../models/userGame';

@Component({
  selector: 'app-user-profile-side-bar',
  imports: [],
  templateUrl: './user-profile-side-bar.component.html',
  styleUrl: './user-profile-side-bar.component.css'
})
export class UserProfileSideBarComponent {
  private _router = inject(Router);

  navigateTo(status: string) {
    this._router.navigate(['/app/user-profile/user-games', status]);
  }

  navigateToReviews() {
    this._router.navigate(['/app/user-profile/reviews']);
  }
}
