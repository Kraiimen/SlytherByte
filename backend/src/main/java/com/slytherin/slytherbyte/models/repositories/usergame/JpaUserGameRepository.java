package com.slytherin.slytherbyte.models.repositories.usergame;

import com.slytherin.slytherbyte.models.entities.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaUserGameRepository extends JpaRepository<UserGame, Integer> {
    @Query("SELECT ug FROM UserGame ug WHERE LOWER(status)=LOWER(:status)")
    List<UserGame> findUserGameByStatus(@Param("status") String status);

    @Query("SELECT ug FROM UserGame ug WHERE ug.status = 'Beaten' AND ug.userProfile.userProfileId = :userProfileId ORDER BY completionDate DESC LIMIT 5")
    List<UserGame> findRecentlyBeatenByProfileId(int userProfileId);

    @Query("SELECT ug FROM UserGame ug WHERE review.reviewId = :reviewId")
    Optional<UserGame> findByReviewId(int reviewId);

    @Query("SELECT ug FROM UserGame ug WHERE ug.userProfile.userProfileId = :userProfileId")
    List<UserGame> findAllByUserProfileId(int userProfileId);

    @Query("SELECT ug FROM UserGame ug WHERE ug.userProfile.userProfileId = :userProfileId AND LOWER(ug.status)=LOWER(:status)")
    List<UserGame> findUserGamesUserProfileIdAndByStatus(int userProfileId, String status);

    @Query("SELECT COUNT(ug) FROM UserGame ug WHERE ug.userProfile.userProfileId = :id AND LOWER(ug.status) = LOWER(:status)")
    int countUserGameByStatus(int id, String status);
}
