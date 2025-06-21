import { Component, inject, OnInit } from '@angular/core';
import { Review } from '../../../../../models/review';
import { ReviewService } from '../../../../../services/reviewService';
import { ReviewComponent } from '../../../../review-component/review/review.component';
import { Router } from '@angular/router';
import { Game } from '../../../../../models/game';
import { GameService } from '../../../../../services/gameService';
import { UserGame } from '../../../../../models/userGame';
import { UserGameService } from '../../../../../services/userGameService';

@Component({
  selector: 'app-user-reviews',
  imports: [ReviewComponent],
  templateUrl: './user-reviews.component.html',
  styleUrl: './user-reviews.component.css'
})
export class UserReviewsComponent implements OnInit {
  private _reviewService = inject(ReviewService);
  private _gameService = inject(GameService);
  reviews!: Review[];
  games: Game[] = [];

  ngOnInit(): void {
    this._reviewService.getAllForLoggedUser().subscribe({
        next: reviews => {
          this.reviews = reviews;

          reviews.forEach(review =>{
            this._gameService.findGameById(review.gameId).subscribe({
              next: game => {
                this.games.push(game);
              },
              error: err => console.log(err)
            });
          });
        },
        error: err => console.log(err)
    });
  }
}
