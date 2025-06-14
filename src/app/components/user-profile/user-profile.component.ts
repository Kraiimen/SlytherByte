import { Component, inject, OnInit } from '@angular/core';
import { UserProfileService } from '../../services/userProfileService';
import { ActivatedRoute, Router } from '@angular/router';
import { UserStatsComponent } from "./user-profile-micro-components/user-stats/user-stats.component";
import { UserProfile } from '../../models/userProfile';
import { UserReviewsComponent } from './user-profile-micro-components/user-reviews/user-reviews/user-reviews.component';

@Component({
  selector: 'app-user-profile',
  imports: [UserStatsComponent, UserReviewsComponent],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
  userProfile!: UserProfile;
  private _userProfileService = inject(UserProfileService);
  private _route = inject(ActivatedRoute);

  constructor() { }

  ngOnInit(): void {
    const id = this._route.snapshot.paramMap.get("id");
    if (id != null) {
      const userProfileId = Number(id);
      if (userProfileId > 0) {
        this._userProfileService.getUserProfileById(userProfileId).subscribe({
          next: userProfile => { this.userProfile = userProfile; console.log(userProfile)},
          error: e => alert('Error bitch')
        });
      } else {
        alert(`No user with id ${userProfileId} found`)
      }
    }
  }

}
