import { Component, HostBinding, inject, Input } from '@angular/core';
import { GameService } from '../../../services/gameService';
import { Filter } from '../../../models/filter';
import { Game } from '../../../models/game';
import { FilterComponentComponent } from "../filter-component/filter-component.component";
import { CatalogueContentComponent } from "../catalogue-content/catalogue-content.component";
import { GameDetailsComponent } from "../../game-details/game-details.component";
import { DeveloperService } from '../../../services/developerService';
import { LanguageService } from '../../../services/languageService';
import { PlatformService } from '../../../services/platformService';
import { PublisherService } from '../../../services/publisherService';
import { StoreService } from '../../../services/storeService';
import { TagService } from '../../../services/tagService';
import { Developer } from '../../../models/developer';
import { Language } from '../../../models/language';
import { Platform } from '../../../models/platform';
import { Publisher } from '../../../models/publisher';
import { Store } from '../../../models/store';
import { Tag } from '../../../models/tag';
import { forkJoin } from 'rxjs';
import { GameMediaService } from '../../../services/gameMediaService';
import { GameMedia } from '../../../models/gameMedia';

@Component({
  selector: 'app-catalogue-main',
  imports: [FilterComponentComponent, CatalogueContentComponent, GameDetailsComponent],
  templateUrl: './catalogue-main.component.html',
  styleUrl: './catalogue-main.component.css'
})
export class CatalogueMainComponent {
  private _gameService = inject(GameService);
  games: Game[] = [];
  @Input() gameId!: number;
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
    this.gameId = gameId;
    this.showDetails = true;
  }

  hideDetails() {
    this.showDetails = false;
    document.body.classList.remove('modal-open');
  }
}
