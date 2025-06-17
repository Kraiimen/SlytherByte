import { Component, EventEmitter, HostBinding, inject, Input, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Language } from '../../models/language';
import { Game } from '../../models/game';
import { Developer } from '../../models/developer';
import { Platform } from '../../models/platform';
import { Publisher } from '../../models/publisher';
import { Store } from '../../models/store';
import { Tag } from '../../models/tag';

@Component({
  selector: 'app-game-details',
  imports: [],
  templateUrl: './game-details.component.html',
  styleUrl: './game-details.component.css'
})
export class GameDetailsComponent {
  private _router = inject(Router);
  private _route = inject(ActivatedRoute);
  @Output() toggleDetails = new EventEmitter<boolean>();
  @Input() gameDetails!: Game;
  @Input() developers!: Developer[];
  @Input() languages!: Language[];
  @Input() platforms!: Platform[];
  @Input() publishers!: Publisher[];
  @Input() stores!: Store[];
  @Input() tags!: Tag[];

  hideDetails() {
    this.toggleDetails.emit(false);
    document.body.classList.remove('modal-open');
  }
}
  

