import { Component, EventEmitter, inject, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Filter } from '../models/filter';
import { FilterComponentComponent } from '../filter-component/filter-component.component'; // Assicurati che il percorso sia corretto
import { GameService } from '../services/gameService';
import { Game } from '../models/game';
import { Observable } from 'rxjs';
import { CatalogueContentComponent } from '../catalogue-content/catalogue-content.component';

@Component({
  selector: 'app-catalogue',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    FilterComponentComponent,
    CatalogueContentComponent
  ],
  templateUrl: './catalogue.component.html',
  styleUrls: ['./catalogue.component.css']
})
export class CatalogueComponent {
  private _gameService = inject (GameService);
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
