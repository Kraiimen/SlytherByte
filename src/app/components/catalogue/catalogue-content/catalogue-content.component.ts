import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Game } from '../../../models/game';

@Component({
  selector: 'app-catalogue-content',
  imports: [CommonModule],
  templateUrl: './catalogue-content.component.html',
  styleUrls: ['./catalogue-content.component.css']
})
export class CatalogueContentComponent {
  @Input() games!: Game[];
  @Output() selectGame = new EventEmitter<number>();

  chooseGame(gameId: number){
    console.log('CATALOGUE-CONTENT: '+gameId);
    this.selectGame.emit(gameId);
  }
}

