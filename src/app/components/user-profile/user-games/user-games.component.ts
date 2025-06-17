import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { UserGameService } from '../../../services/userGameService';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { UserGame } from '../../../models/userGame';
import { map, switchMap } from 'rxjs';
import { GameService } from '../../../services/gameService';
import { Game } from '../../../models/game';

@Component({
  selector: 'app-user-games',
  imports: [RouterModule],
  templateUrl: './user-games.component.html',
  styleUrl: './user-games.component.css'
})
export class UserGamesComponent implements OnInit {
  private _userGameService = inject(UserGameService);
  private _gameService = inject(GameService);
  private _route = inject(ActivatedRoute);
  status: string | null = '';
  userGames: UserGame[] = [];
  games: Game[] = [];

  ngOnInit(): void {
    this._route.paramMap.subscribe(paramMap => {
      this.status = paramMap.get('status');
      this.loadGames(this.status!);
    })
  }

  loadGames(status: string): void {
    this.userGames = [];
    this.games = [];
    
    this._userGameService.getAllByStatusForLoggedUser(status).subscribe({
      next: userGames => {
        this.userGames = userGames;
        console.log(userGames);
        userGames.forEach(ug => {
          this._gameService.findGameById(ug.gameId)
            .subscribe(game => {
              console.log(game);
              this.games.push(game);
            });
        });
      },
      error: e => alert('Failed to find games with status ' + status)
    });
  }
}
