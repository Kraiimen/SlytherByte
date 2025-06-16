import { Component, inject, OnInit } from '@angular/core';
import { Review } from '../../../../../models/review';
import { ReviewService } from '../../../../../services/reviewService';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-reviews',
  imports: [],
  templateUrl: './user-reviews.component.html',
  styleUrl: './user-reviews.component.css'
})
export class UserReviewsComponent implements OnInit{
  reviews!: Review[];
  private _reviewService = inject(ReviewService);
  private _route = inject(ActivatedRoute);

  constructor(){}
  
  ngOnInit(): void {
    this._reviewService.getAll().subscribe({
      next: (result) => { this.reviews = result; },
      error: e => alert("Error loading reviews")
    })
  }

}
