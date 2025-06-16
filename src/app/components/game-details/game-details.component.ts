import { Component, inject, Input } from '@angular/core';
import { GameDetails } from '../../models/gameDetails';
import { GameService } from '../../services/gameService';
import { ActivatedRoute } from '@angular/router';
import { Game } from '../../models/game';

@Component({
  selector: 'app-game-details',
  imports: [],
  templateUrl: './game-details.component.html',
  styleUrl: './game-details.component.css'
})
export class GameDetailsComponent {
  game!: Game;
  private _gameService = inject(GameService);
  private _route = inject(ActivatedRoute);
  
  @Input() gameDetails!: Game;
  
}
  

