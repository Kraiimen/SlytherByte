package com.slytherin.slytherbyte.models.services.review;

import com.slytherin.slytherbyte.models.entities.Review;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews() throws DataException;
    Review findReviewById(int reviewId) throws DataException, EntityNotFoundException;
    Review createReview(Review review, int gameId, int userProfileId) throws DataException, EntityNotFoundException;
    Review updateReview(Review review, int gameId, int userProfileId) throws DataException, EntityNotFoundException;
    boolean deleteReviewById(int reviewId) throws DataException, EntityNotFoundException;
    List<Review> findRecentReviews() throws DataException;
    List<Review> findAllByProfileId(int profileId) throws DataException, EntityNotFoundException;
}
