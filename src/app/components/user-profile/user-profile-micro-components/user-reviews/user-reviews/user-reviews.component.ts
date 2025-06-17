import { Component, inject, OnInit } from '@angular/core';
import { Review } from '../../../../../models/review';
import { ReviewService } from '../../../../../services/reviewService';
import { ReviewComponent } from '../../../../review/review.component';

@Component({
  selector: 'app-user-reviews',
  imports: [ReviewComponent],
  templateUrl: './user-reviews.component.html',
  styleUrl: './user-reviews.component.css'
})
export class UserReviewsComponent implements OnInit {
  reviews!: Review[];
  private _reviewService = inject(ReviewService);

  ngOnInit(): void {
      this._reviewService.getAllForLoggedUser().subscribe({
          next: reviews => this.reviews = reviews,
          error: err => console.log(err)
      });
  }
}
