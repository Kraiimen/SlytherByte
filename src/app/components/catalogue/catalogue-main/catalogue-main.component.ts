import { Component, inject } from '@angular/core';
import { GameService } from '../../../services/gameService';
import { Filter } from '../../../models/filter';
import { Game } from '../../../models/game';
import { FilterComponentComponent } from "../filter-component/filter-component.component";
import { CatalogueContentComponent } from "../catalogue-content/catalogue-content.component";

@Component({
  selector: 'app-catalogue-main',
  imports: [FilterComponentComponent, CatalogueContentComponent],
  templateUrl: './catalogue-main.component.html',
  styleUrl: './catalogue-main.component.css'
})
export class CatalogueMainComponent {
  private _gameService = inject(GameService);
  filters: Partial<Filter> = {};
  games: Game[] = [];
  

  onFiltersChanged(newFilters: Partial<Filter>) {
    this.filters = newFilters;
    this._gameService.getGames(this.filters).subscribe({
      next: games => this.games = games,
      error: e => console.log("Service not found")
    });
  }
}
