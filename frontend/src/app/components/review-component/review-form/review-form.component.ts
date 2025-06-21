import { Component, EventEmitter, Inject, inject, Input, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReviewService } from '../../../services/reviewService';
import { Review } from '../../../models/review';
import { UserGameService } from '../../../services/userGameService';
import { GameService } from '../../../services/gameService';
import { UserGame } from '../../../models/userGame';

@Component({
  selector: 'app-review-form',
  imports: [ReactiveFormsModule],
  templateUrl: './review-form.component.html',
  styleUrl: './review-form.component.css'
})
export class ReviewFormComponent {
  private _reviewService = inject(ReviewService);
  private _userGameService = inject(UserGameService);
  private _gameService = inject(GameService);
  private _formBuilder = inject(FormBuilder);
  @Output() closeForm = new EventEmitter<boolean>();
  @Input() gameId!: number;
  @Input() userGameId!: number;
  reviewForm!: FormGroup;
  userGame!: UserGame;

  constructor() {
    this.reviewForm = this._formBuilder.group({
      title: ["", [Validators.required]],
      description: ["", [Validators.required]],
      rating: [1, Validators.required]
    });
  }

  onSubmit() {
    const review: Partial<Review> = {
      ...this.reviewForm.value,
      gameId: this.gameId
    }
    this._reviewService.createReview(review).subscribe({
      next: () => console.log('Review added successfully'),
      error: e => console.log('Error adding a review')
    });

    this._gameService.findGameById(this.gameId).subscribe({
      next: game => {
        
      },
      error: e => console.log(e)
    });

    this.closeReviewForm();
  }

  closeReviewForm() {
    this.closeForm.emit(true);
  }
}
