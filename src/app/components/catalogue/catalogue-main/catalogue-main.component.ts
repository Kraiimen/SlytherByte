import { Component, HostBinding, inject } from '@angular/core';
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

@Component({
  selector: 'app-catalogue-main',
  imports: [FilterComponentComponent, CatalogueContentComponent, GameDetailsComponent],
  templateUrl: './catalogue-main.component.html',
  styleUrl: './catalogue-main.component.css'
})
export class CatalogueMainComponent {
  private _gameService = inject(GameService);
  private _developerService = inject(DeveloperService);
  private _languageService = inject(LanguageService);
  private _platformService = inject(PlatformService);
  private _publisherService = inject(PublisherService);
  private _storeService = inject(StoreService);
  private _tagService = inject(TagService);
  games: Game[] = [];
  game!: Game;
  developers: Developer[] = [];
  languages: Language[] = [];
  platforms: Platform[] = [];
  publishers: Publisher[] = [];
  stores: Store[] = [];
  tags: Tag[] = [];
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
    document.body.classList.add('modal-open');
    forkJoin([this._gameService.findGameById(gameId), this._developerService.getDevelopersByGameId(gameId), this._languageService.getLanguagesByGameId(gameId),
              this._platformService.getPlatformsByGameId(gameId), this._publisherService.getPublishersByGameId(gameId), this._storeService.getStoresByGameId(gameId),
              this._tagService.getTagsByGameId(gameId)])
          .subscribe({
            next: result => {
              this.game = result[0];
              this.developers = result[1];
              this.languages = result[2];
              this.platforms = result[3];
              this.publishers = result[4];
              this.stores = result[5];
              this.tags = result[6];
              this.showDetails = true;
            },
            error: e => console.log("Fork Join error" + e)
          });
  }

  hideDetails() {
    this.showDetails = false;
    document.body.classList.remove('modal-open');
  }
}
