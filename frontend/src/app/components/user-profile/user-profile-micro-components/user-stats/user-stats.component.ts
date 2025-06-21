import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserStats } from '../../../../models/userStats';
import { UserStatsService } from '../../../../services/userStatsService';
import { forkJoin } from 'rxjs';
import { Top5Tags } from '../../../../models/top5Tags';

@Component({
  selector: 'app-user-stats',
  imports: [],
  templateUrl: './user-stats.component.html',
  styleUrl: './user-stats.component.css'
})
export class UserStatsComponent implements OnInit{
  userStats: UserStats = {
    top5Tags:[],
    gamesOwned: 0,
    gamesPlaying: 0,
    gamesBeaten: 0,
    userReviews: 0
  };
  private _userStatsService = inject(UserStatsService);
  private _route = inject(ActivatedRoute);

  loading = false;
  errorMessage: string | null = null;


  constructor() {}

  ngOnInit(): void {
    this.loadAllStats();
  }

  private loadAllStats(): void {
    this.loading = true;
    this.errorMessage = null;

    forkJoin([
      this._userStatsService.getTop5Tags(),
      this._userStatsService.getNumberGamesPlaying(), 
      this._userStatsService.getNumberGamesBeaten(),
      this._userStatsService.getCountReviews()
    ])
      .subscribe({
        next: (results) => {
          this.userStats.top5Tags = results[0];
          this.userStats.gamesPlaying = results[1];
          this.userStats.gamesBeaten = results[2];
          this.userStats.userReviews = results[3];
        },
        error: (err) => this.errorMessage = 'Failed to load user stats'
        });
  }
}
  
