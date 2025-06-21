import { Component, HostBinding, inject, Input, OnInit } from '@angular/core';
import { Review } from '../../../models/review';
import { ActivatedRoute } from '@angular/router';
import { Game } from '../../../models/game';

@Component({
  selector: 'app-review',
  imports: [],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {
  private _route = inject(ActivatedRoute);
  @Input() review!: Review;
  @Input() game!: Game;
  @HostBinding('class') hostClass = '';

  ngOnInit(): void {
    this._route.data.subscribe(data => {
      this.hostClass = data['bgType'];
    });
  }
}
