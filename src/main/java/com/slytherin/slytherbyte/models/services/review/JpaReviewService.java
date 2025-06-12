package com.slytherin.slytherbyte.models.services.review;

import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.entities.Review;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.repositories.game.JpaGameRepository;
import com.slytherin.slytherbyte.models.repositories.review.JpaReviewRepository;
import com.slytherin.slytherbyte.models.repositories.userprofile.JpaUserProfileRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaReviewService implements ReviewService {
    private JpaReviewRepository reviewRepo;
    private JpaGameRepository gameRepo;
    private JpaUserProfileRepository profileRepo;

    public JpaReviewService(JpaReviewRepository reviewRepository, JpaGameRepository gameRepo, JpaUserProfileRepository profileRepo){
        this.reviewRepo = reviewRepository;
        this.gameRepo = gameRepo;
        this.profileRepo = profileRepo;
    }

    @Override
    public List<Review> findAllReviews() throws DataException {
        try{
            return reviewRepo.findAll();
        } catch(PersistenceException pe){
            throw new DataException("Failed to find reviews", pe);
        }
    }

    @Override
    public Review findReviewById(int reviewId) throws DataException, EntityNotFoundException {
        try{
            Optional<Review> or = reviewRepo.findById(reviewId);
            if(or.isEmpty()){
                throw new EntityNotFoundException(Review.class, reviewId);
            }
            return or.get();
        } catch(PersistenceException pe){
            throw new DataException("Failed to find review with Id " + reviewId, pe);
        }
    }

    @Override
    @Transactional
    public Review createReview(Review review, int gameId, int userProfileId) throws DataException, EntityNotFoundException {
        try{
            Optional<Game> gm = gameRepo.findById(gameId);
            if (gm.isEmpty()){
                throw new EntityNotFoundException(Game.class, gameId);
            }
            Optional<UserProfile> up = profileRepo.findById(userProfileId);
            if (up.isEmpty()){
                throw new EntityNotFoundException(UserProfile.class, userProfileId);
            }
            review.setUserProfile(up.get());
            review.setGame(gm.get());
            return reviewRepo.save(review);
        } catch(PersistenceException pe){
            throw new DataException("Failed to create new review", pe);
        }
    }

    @Override
    public Review updateReview(Review review, int gameId, int userProfileId) throws DataException, EntityNotFoundException {
        return null;
    }

    @Override
    public boolean deleteReviewById(int reviewId) throws DataException {
        return false;
    }
}
