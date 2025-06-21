package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.UserGame;

import java.time.LocalDate;

public record UserGameDto(int userGameId, int userProfileId, String status, Boolean isOwned,
                          Integer reviewId, LocalDate completionDate, int gameId) {

    public static UserGameDto toDto(UserGame userGame) {
        Integer reviewId = null;
        if (userGame.getReview() != null) {
            reviewId = userGame.getReview().getReviewId();
        }

        return new UserGameDto(userGame.getUserGameId(), userGame.getUserProfile().getUserProfileId(),
                userGame.getStatus(), userGame.isOwned(), reviewId, userGame.getCompletionDate(), userGame.getGame().getGameId());
    }

    public UserGame toEntity() {
        return new UserGame(this.userGameId, this.status, this.isOwned, null, this.completionDate, null, null);
    }
}
