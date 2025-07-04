import { Component, HostBinding, inject, Input } from '@angular/core';
import { GameService } from '../../../services/gameService';
import { Filter } from '../../../models/filter';
import { Game } from '../../../models/game';
import { FilterComponentComponent } from "../filter-component/filter-component.component";
import { CatalogueContentComponent } from "../catalogue-content/catalogue-content.component";
import { GameDetailsComponent } from "../../game-details/game-details.component";

@Component({
  selector: 'app-catalogue-main',
  imports: [FilterComponentComponent, CatalogueContentComponent, GameDetailsComponent],
  templateUrl: './catalogue-main.component.html',
  styleUrl: './catalogue-main.component.css'
})
export class CatalogueMainComponent {
  private _gameService = inject(GameService);
  games: Game[] = [];
  gameId!: number;
  showDetails: boolean = false;
  
  ngOnInit(){
    this._gameService.getAllGames().subscribe({
      next: games => this.games = games,
      error: e => console.log("Service not found")
    });
  }
  
  onFiltersChanged(newFilters: Partial<Filter>) {
    this._gameService.searchGames(newFilters).subscribe({
      next: games => this.games = games,
      error: e => console.log("Service not found")
    });
  }

  getGameDetails(gameId: number){
    console.log('CATALOGUE-MAIN:' + gameId)
    this.gameId = gameId;
    this.showDetails = true;
  }

  hideDetails() {
    this.showDetails = false;
    document.body.classList.remove('modal-open');
  }
}
