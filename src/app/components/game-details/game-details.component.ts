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
import { EMPTY, forkJoin, map, switchMap } from 'rxjs';
import { UserGameService } from '../../services/userGameService';
import { UserGame } from '../../models/userGame';
import { escapeRegExp } from '@angular/compiler';
import { ReviewFormComponent } from '../review-component/review-form/review-form.component';
import { Review } from '../../models/review';
import { ReviewService } from '../../services/reviewService';
import { ReviewComponent } from "../review-component/review/review.component";

@Component({
  selector: 'app-game-details',
  imports: [ReviewFormComponent, ReviewComponent],
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
  private _reviewService = inject(ReviewService);
  @Output() toggleDetails = new EventEmitter<boolean>();
  @Input() gameId!: number;
  gameDetails!: Game;
  userGame!: UserGame;
  userGames!: UserGame[];
  developers: Developer[] = [];
  languages: Language[] = [];
  platforms: Platform[] = [];
  publishers: Publisher[] = [];
  stores: Store[] = [];
  tags: Tag[] = [];
  gameTrailer!: GameMedia;
  gameImages!: GameMedia[];
  media: GameMedia[] = [];
  reviews: Review[] = [];
  sliderPos = 0;   
  @ViewChild('sliderMain', {static: false}) sliderMain!: ElementRef<HTMLDivElement>;
  isAddProfileOpen = false;
  areThereCollections = false;
  isGameInPlaying = false;
  isGameInBeaten = false;
  isGameInWishlist = false;
  isReviewFormShown = false;
  showIFrame = false;
  isInUserGames = false;

  ngOnInit(): void {
    this.isInUserGames = false;
    this.resetStatusVariables();
    this.getGameDetails(this.gameId);   
    this.checkIfGameIsInStatus();
    this.loadReviews();
    this.loadUserGames();
  }

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

  addGameTo(status: string) {
    const userGame: Partial<UserGame> = { 
      gameId: this.gameDetails.gameId,
      status
    };

    if(status == 'Beaten') {
      userGame.completionDate = new Date().toISOString().split('T')[0];
    }
    
      this._userGameService.createUserGameForLoggedUser(userGame).subscribe({
      next: ug => {
        console.log('Game added to profile in status ' + status);
        console.log(ug);
      },
      error: e => console.log(e)
    });

    this.checkIfGameIsInUserGames();
  }

  loadUserGames() {
    this.reviews.forEach(r => {
      this._userGameService.getUserGameByReviewId(r.reviewId).subscribe({
        next: ug => this.userGames.push(ug),
        error: e => console.log(e)
      });
    });
  }

  loadReviews() {
    this._reviewService.getAllByGameId(this.gameId).subscribe({
      next: r => this.reviews = r,
      error: e => console.log(e)
    });
  }

  checkIfGameIsInStatus() {
    this._userGameService.getAllForLoggedUser().subscribe({
      next: userGames => {
        const game = userGames.find(g => g.gameId == this.gameDetails.gameId);
        if (game?.status == 'Playing') {
          this.isGameInPlaying = true;
        } else if (game?.status == 'Beaten') {
          this.isGameInBeaten = true;
        } else if (game?.status == 'Wishlist') {
          this.isGameInWishlist = true;
        }
      }
    });
  }

  checkIfGameIsInUserGames() {
    this._userGameService.getAllForLoggedUser().subscribe({
      next: userGames => {
        userGames.forEach(ug => {
          if (ug.gameId == this.gameId) {
            this.isInUserGames = true;
          }
        });
      }
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

  resetStatusVariables() {
    this.isGameInPlaying = false;
    this.isGameInBeaten = false;
    this.isGameInWishlist = false;
  }

  getThumbnail(url: String): string {
    const vid = url.split('embed/')[1].split('?')[0];
    return `https://i.ytimg.com/vi/${vid}/hqdefault.jpg`;
  }
}
  

