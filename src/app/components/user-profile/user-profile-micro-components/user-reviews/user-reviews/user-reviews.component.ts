import { Component, inject, OnInit } from '@angular/core';
import { Review } from '../../../../../models/review';
import { ReviewService } from '../../../../../services/reviewService';
import { ReviewComponent } from '../../../../review/review.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-reviews',
  imports: [],
  templateUrl: './user-reviews.component.html',
  styleUrl: './user-reviews.component.css'
})
export class UserReviewsComponent implements OnInit {
  reviews!: Review[];
  private _router = inject(Router);
  private _reviewService = inject(ReviewService);

  ngOnInit(): void {
      this._reviewService.getAllForLoggedUser().subscribe({
          next: reviews => this.reviews = reviews,
          error: err => console.log(err)
      });
  }

  onSumbit() {
    this._router.navigate(["/app/user-profile/add-review"]);
  }
}
