package com.slytherin.slytherbyte.models.repositories.views;

import com.slytherin.slytherbyte.models.entities.views.stats.UserReviewsCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserReviewCountRepository extends JpaRepository<UserReviewsCount, Integer> {
}
