import { Component, inject, Input, OnInit } from '@angular/core';
import { Review } from '../../models/review';
import { ReviewService } from '../../services/reviewService';
import { ActivatedRoute } from '@angular/router';
import { UserGameService } from '../../services/userGameService';
import { UserGame } from '../../models/userGame';

@Component({
  selector: 'app-review',
  imports: [],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {
  private _userGameService = inject(UserGameService);
  @Input() review!: Review;
  userGame!: UserGame;

  ngOnInit(): void {
    this._userGameService.getUserGameByReviewId(this.review.reviewId).subscribe({
      next: userGame => this.userGame = userGame,
      error: err => console.log(err)
    });
  }
}
