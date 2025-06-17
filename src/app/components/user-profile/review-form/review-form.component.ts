import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReviewService } from '../../../services/reviewService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-form',
  imports: [ReactiveFormsModule],
  templateUrl: './review-form.component.html',
  styleUrl: './review-form.component.css'
})
export class ReviewFormComponent {
  formBuilder = inject(FormBuilder);
  reviewForm!: FormGroup;
  private _reviewService = inject(ReviewService);
  private _router = inject(Router);

  constructor() {
    this.reviewForm = this.formBuilder.group({
      title: ["", [Validators.required]],
      description: ["", [Validators.required]]
    })
  }

  onSubmit() {
    this._reviewService.createReview(this.reviewForm.value).subscribe({
      next: result => {
        this._router.navigate(['/reviews'])
      },
      error: e => console.log('Error adding a review')
    });
  }


}
