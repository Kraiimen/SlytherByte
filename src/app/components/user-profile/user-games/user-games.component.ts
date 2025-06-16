import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { UserGameService } from '../../../services/userGameService';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { UserGame } from '../../../models/userGame';

@Component({
  selector: 'app-user-games',
  imports: [RouterModule],
  templateUrl: './user-games.component.html',
  styleUrl: './user-games.component.css'
})
export class UserGamesComponent implements OnInit {
  private _userGameService = inject(UserGameService);
  private _route = inject(ActivatedRoute);
  userGames: UserGame[] = [];

  ngOnInit(): void {
    this._route.paramMap.subscribe(paramMap => {
      const status=paramMap.get('status');
      this.loadGames(status!);
    })

  }

  loadGames(status: string): void {
    this._userGameService.getAllByStatus(status).subscribe({
      next: data => {
        this.userGames = data;
        console.log(this.userGames);
      },
      error: e => alert('Failed to find games with status ' + status)
    });
  }
}
