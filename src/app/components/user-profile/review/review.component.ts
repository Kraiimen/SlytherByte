import { Component, inject } from '@angular/core';
import { Review } from '../../../models/review';
import { ReviewService } from '../../../services/reviewService';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-review',
  imports: [],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {
  review!: Review;
  private _reviewService = inject(ReviewService);
  private _route = inject(ActivatedRoute);
  private _router = inject(Router);

  ngOnInit(): void {
    const reviewId = Number(this._route.snapshot.paramMap.get('id'));
    if (!isNaN(reviewId))
      this._reviewService.getReviewById(reviewId).subscribe({
        next: (data) => this.review = data,
        error: e => console.log('Error getting a review', e)
      });
  }

  onSumbit() {
    this._router.navigate(["/add-review"]);
  }
}
