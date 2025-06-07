package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.entities.Review;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import org.apache.catalina.User;

import java.time.LocalDate;

public record ReviewDto(int reviewId, String title, LocalDate date, int userProfileId, int gameId) {

    public static ReviewDto toDto(Review review){
        return new ReviewDto(review.getReviewId(), review.getTitle(), review.getDate(), review.getUserProfile().getUserProfileId(), review.getGame().getGameId());
    }

    public Review toEntity(){
        return new Review(this.reviewId, this.title, this.date, null, null);
    }
}
