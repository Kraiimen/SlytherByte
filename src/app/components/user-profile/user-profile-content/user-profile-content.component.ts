import { Component, inject } from '@angular/core';
import { UserStatsComponent } from '../user-profile-micro-components/user-stats/user-stats.component';
import { UserReviewsComponent } from '../user-profile-micro-components/user-reviews/user-reviews/user-reviews.component';
import { UserProfile } from '../../../models/userProfile';
import { UserProfileService } from '../../../services/userProfileService';

@Component({
  selector: 'app-user-profile-content',
  imports: [UserStatsComponent, UserReviewsComponent],
  templateUrl: './user-profile-content.component.html',
  styleUrl: './user-profile-content.component.css'
})
export class UserProfileContentComponent {
  userProfile!: UserProfile;
  private _userProfileService = inject(UserProfileService);

  ngOnInit(): void {
    this._userProfileService.getLoggedUserProfile().subscribe({
      next: userProfile => { this.userProfile = userProfile; console.log(userProfile) },
      error: e => alert('Error bitch')
    });
  }
}
