package com.slytherin.slytherbyte.models.repositories.review;

import com.slytherin.slytherbyte.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends JpaRepository<Review, Integer> {

}
