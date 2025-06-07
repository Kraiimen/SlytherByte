package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Game;

import java.time.LocalDate;

public record GameDto(int gameId, String coverImageUrl, LocalDate realeaseDate, String summary, int franchiseId) {

    public static GameDto toDto(Game game){
        return new GameDto(game.getGameId(), game.getCoverImageUrl(), game.getReleaseDate(), game.getSummary(), game.getFranchise().getFranchiseId());
    }

    public Game toEntity(){
        return new Game(this.gameId, this.coverImageUrl, this.realeaseDate, this.summary, null);
    }
}
