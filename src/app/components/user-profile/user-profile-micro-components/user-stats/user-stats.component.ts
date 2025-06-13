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

    // Use forkJoin to run all requests in parallel
    forkJoin(this._userStatsService.sources)
      .subscribe({
      next: (results) => {
        this.userStats.top5Tags = results[0] as Top5Tags[];
        this.userStats.gamesOwned = results[1] as number;
        this.userStats.gamesPlaying = results[2] as number;
        this.userStats.gamesBeaten = results[3] as number;
        this.userStats.userReviews = results[4] as number;
      },
      error: (err) => {
        // In principle, forkJoin only errors if something outside our inner catchError happens.
        this.errorMessage = 'Failed to load user stats';
      }
    });
  }
}
  
