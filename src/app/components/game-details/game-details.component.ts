import { Component, inject, Input } from '@angular/core';
import { GameDetails } from '../../models/gameDetails';
import { GameService } from '../../services/gameService';
import { ActivatedRoute } from '@angular/router';
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
  private _gameService = inject(GameService);
  private _route = inject(ActivatedRoute);
  
  @Input() gameDetails!: Game;
  @Input() developers!: Developer[];
  @Input() languages!: Language[];
  @Input() platforms!: Platform[];
  @Input() publishers!: Publisher[];
  @Input() stores!: Store[];
  @Input() tags!: Tag[];
}
  

