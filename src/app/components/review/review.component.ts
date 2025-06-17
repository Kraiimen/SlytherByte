import { Component, Host, HostBinding, inject, Input, OnInit } from '@angular/core';
import { Review } from '../../models/review';
import { UserGameService } from '../../services/userGameService';
import { UserGame } from '../../models/userGame';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-review',
  imports: [],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {
  private _userGameService = inject(UserGameService);
  private _route = inject(ActivatedRoute);
  @Input() review!: Review;
  userGame!: UserGame;
  @HostBinding('class') hostClass = '';

  ngOnInit(): void {
    this._userGameService.getUserGameByReviewId(this.review.reviewId).subscribe({
      next: userGame => this.userGame = userGame,
      error: err => console.log(err)
    });

    this._route.data.subscribe(data => {
      this.hostClass = data['bgType'];
    });
  }
}
