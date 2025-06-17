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

    @Query("SELECT ug FROM UserGame ug ORDER BY completionDate DESC LIMIT 2")
    List<UserGame> findUserGamesByCompletionDate();

    @Query("SELECT ug FROM UserGame ug WHERE review.reviewId = :reviewId")
    Optional<UserGame> findByReviewId(int reviewId);
}
