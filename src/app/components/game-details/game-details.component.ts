import { Component, ElementRef, EventEmitter, inject, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Language } from '../../models/language';
import { Game } from '../../models/game';
import { Developer } from '../../models/developer';
import { Platform } from '../../models/platform';
import { Publisher } from '../../models/publisher';
import { Store } from '../../models/store';
import { Tag } from '../../models/tag';
import { GameMedia } from '../../models/gameMedia';
import { DomSanitizer } from '@angular/platform-browser';
import { DeveloperService } from '../../services/developerService';
import { LanguageService } from '../../services/languageService';
import { PlatformService } from '../../services/platformService';
import { PublisherService } from '../../services/publisherService';
import { StoreService } from '../../services/storeService';
import { TagService } from '../../services/tagService';
import { GameMediaService } from '../../services/gameMediaService';
import { GameService } from '../../services/gameService';
import { forkJoin } from 'rxjs';
import { UserGameService } from '../../services/userGameService';
import { UserGame } from '../../models/userGame';

@Component({
  selector: 'app-game-details',
  imports: [],
  templateUrl: './game-details.component.html',
  styleUrl: './game-details.component.css'
})
export class GameDetailsComponent implements OnInit {
  private _sanitizer = inject(DomSanitizer);
  private _gameService = inject(GameService);
  private _developerService = inject(DeveloperService);
  private _languageService = inject(LanguageService);
  private _platformService = inject(PlatformService);
  private _publisherService = inject(PublisherService);
  private _storeService = inject(StoreService);
  private _tagService = inject(TagService);
  private _gameMediaService = inject(GameMediaService);
  private _userGameService = inject(UserGameService);
  @Output() toggleDetails = new EventEmitter<boolean>();
  @Input() gameId!: number;
  gameDetails!: Game;
  developers: Developer[] = [];
  languages: Language[] = [];
  platforms: Platform[] = [];
  publishers: Publisher[] = [];
  stores: Store[] = [];
  tags: Tag[] = [];
  gameTrailer!: GameMedia;
  gameImages!: GameMedia[];
  media: GameMedia[] = [];
  sliderPos = 0;   
  @ViewChild('sliderMain', {static: false}) sliderMain!: ElementRef<HTMLDivElement>;
  isAddProfileOpen = false;
  areThereCollections = false;
  userGame: Partial<UserGame> = {};
  
  getGameDetails(gameId: number){
    document.body.classList.add('modal-open');
    forkJoin([
      this._gameService.findGameById(gameId), this._developerService.getDevelopersByGameId(gameId), this._languageService.getLanguagesByGameId(gameId),
      this._platformService.getPlatformsByGameId(gameId), this._publisherService.getPublishersByGameId(gameId), this._storeService.getStoresByGameId(gameId),
      this._tagService.getTagsByGameId(gameId)
    ]).subscribe({
            next: result => {
              this.gameDetails = result[0];
              this.developers = result[1];
              this.languages = result[2];
              this.platforms = result[3];
              this.publishers = result[4];
              this.stores = result[5];
              this.tags = result[6];
            },
            error: e => console.log("Fork Join error" + e)
          });
    forkJoin([
       this._gameMediaService.getTrailerByGameId(gameId), this._gameMediaService.getImagesByGameId(gameId)
    ]).subscribe({
      next: result => {
        this.gameTrailer = result[0];
        this.gameImages = result[1];
        this.media.push(this.gameTrailer);
        this.gameImages.forEach(element => this.media.push(element));
        console.log(this.media)
      },
      error: e => console.log("Media service error" + e)
    });
  }

  ngOnInit(): void {
      this.getGameDetails(this.gameId);   
  }

  addGameTo(stauts: string) {
    this.userGame.gameId = this.gameDetails.gameId;
    this.userGame.status = stauts;
    this._userGameService.createUserGameForLoggedUser(this.userGame).subscribe({
      next: ug => {
        console.log('Game added to profile');
        console.log(ug);
      },
      error: e => console.log(e)
    });
  }

  move(dir: number) {
    this.sliderPos += dir;
    if (this.sliderPos < 0) {
      this.sliderPos = 0;
    }
    if (this.sliderPos > this.media.length) {
      this.sliderPos = this.media.length;
    }

    const t = -33*(this.sliderPos / this.media.length);
    this.sliderMain.nativeElement.style.transform = `translateX(${t}%)`;
  }

  safe(url: string) { 
    return this._sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  hideDetails() {
    this.toggleDetails.emit(false);
    document.body.classList.remove('modal-open');
  }
}
  

