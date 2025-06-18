import { Component, inject } from '@angular/core';
import { UserStatsComponent } from '../user-profile-micro-components/user-stats/user-stats.component';
import { UserReviewsComponent } from '../user-profile-micro-components/user-reviews/user-reviews/user-reviews.component';
import { UserProfile } from '../../../models/userProfile';
import { UserProfileService } from '../../../services/userProfileService';
import { UserGameService } from '../../../services/userGameService';
import { UserGame } from '../../../models/userGame';
import { Game } from '../../../models/game';
import { GameService } from '../../../services/gameService';

@Component({
  selector: 'app-user-profile-content',
  imports: [UserStatsComponent],
  templateUrl: './user-profile-content.component.html',
  styleUrl: './user-profile-content.component.css'
})
export class UserProfileContentComponent {
  private _userProfileService = inject(UserProfileService);
  private _userGameService = inject(UserGameService);
  private _gameService = inject(GameService);
  userProfile!: UserProfile;
  recentlyBeaten: UserGame[] = [];
  recentlyBeatenGames: Game[] = [];
  
  ngOnInit(): void {
    this.loadUserProfile();
    this.loadRecentGameBeaten();
  }

  loadUserProfile(): void {
    this._userProfileService.getLoggedUserProfile().subscribe({
      next: userProfile => { this.userProfile = userProfile; console.log(userProfile) },
      error: e => console.log(e)
    });
  }

  loadRecentGameBeaten(): void {
    this._userGameService.getRecentlyBeaten().subscribe({
      next: userGames => {
        this.recentlyBeaten = userGames;
        userGames.forEach(ug => {
          this._gameService.findGameById(ug.gameId).subscribe(game => {
            this.recentlyBeatenGames.push(game);
          })
        });
      },
      error: e => console.log(e)
    });
  }
}
