package com.slytherin.slytherbyte.models.repositories.review;

import com.slytherin.slytherbyte.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r ORDER BY date DESC LIMIT 2")
    List<Review> findReviewsByDate();

    @Query("SELECT r FROM Review r WHERE r.userProfile.userProfileId = :profileId")
    List<Review> findAllByProfileId(int profileId);

    @Query("SELECT r FROM Review r WHERE r.game.gameId = :gameId")
    List<Review> findAllByGameId(int gameId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.userProfile.userProfileId = :userProfileId")
    int countReviewsByProfileId(int userProfileId);
}
