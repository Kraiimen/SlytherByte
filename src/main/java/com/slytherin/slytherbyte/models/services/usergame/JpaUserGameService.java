package com.slytherin.slytherbyte.models.services.usergame;

import com.slytherin.slytherbyte.models.entities.*;
import com.slytherin.slytherbyte.models.entities.views.stats.*;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.repositories.usergame.JpaUserGameRepository;
import com.slytherin.slytherbyte.models.repositories.game.JpaGameRepository;
import com.slytherin.slytherbyte.models.repositories.review.JpaReviewRepository;
import com.slytherin.slytherbyte.models.repositories.userprofile.JpaUserProfileRepository;
import com.slytherin.slytherbyte.models.repositories.views.*;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserGameService implements UserGameService {
    private JpaUserGameRepository userGameRepo;
    private JpaGameRepository gameRepo;
    private JpaUserProfileRepository userProfileRepo;
    private JpaReviewRepository reviewRepo;
    private JpaOwnedGamesRepository ownedGamesRepo;
    private JpaTop5TagsRepository top5TagsRepo;
    private JpaUserGamesPlayingRepository gamesPlayingRepo;
    private JpaUserGamesBeatenRepository gamesBeatenRepo;
    private JpaUserReviewCountRepository reviewCountRepo;


    public JpaUserGameService(JpaUserGameRepository userGameRepo, JpaGameRepository gameRepo,
                              JpaUserProfileRepository userProfileRepo, JpaReviewRepository reviewRepo,
                              JpaOwnedGamesRepository ownedGamesRepo, JpaTop5TagsRepository top5TagsRepo,
                              JpaUserGamesPlayingRepository gamesPlayingRepo, JpaUserGamesBeatenRepository gamesBeatenRepo,
                              JpaUserReviewCountRepository reviewCountRepo) {
        this.userGameRepo = userGameRepo;
        this.gameRepo = gameRepo;
        this.userProfileRepo = userProfileRepo;
        this.reviewRepo=reviewRepo;
        this.ownedGamesRepo=ownedGamesRepo;
        this.top5TagsRepo=top5TagsRepo;
        this.gamesPlayingRepo=gamesPlayingRepo;
        this.gamesBeatenRepo=gamesBeatenRepo;
        this.reviewCountRepo=reviewCountRepo;
    }

    @Override
    public List<UserGame> findAllUserGames() throws DataException {
        try {
            return userGameRepo.findAll();
        } catch (PersistenceException pe) {
            throw new DataException("Error: no games found", pe);
        }
    }

    @Override
    public UserGame findUserGameById(int userGameId) throws DataException, EntityNotFoundException {
        try {
            Optional<UserGame> userGame = userGameRepo.findById(userGameId);
            if (userGame.isEmpty())
                throw new EntityNotFoundException(UserGame.class, userGameId);
            return userGame.get();
        } catch (PersistenceException pe) {
            throw new DataException("No games with such id found", pe);
        }
    }

    @Override
    public UserGame createUserGame(UserGame userGame, int gameId,
                                   int userProfileId, Integer reviewId) throws DataException, EntityNotFoundException {
        try {
            Game g = gameRepo.findById(gameId)
                    .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));

            UserProfile up = userProfileRepo.findById(userProfileId)
                    .orElseThrow(() -> new EntityNotFoundException(UserProfile.class, userProfileId));

            if(reviewId!=null) {
                Review r = reviewRepo.findById(reviewId)
                        .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));
                userGame.setReview(r);
            }

            userGame.setUserProfile(up);
            userGame.setGame(g);
           return userGameRepo.save(userGame);
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: failed to add game to user profile", persistenceException);
        }
    }

    @Override
    public UserGame updateUserGame(UserGame userGame, int gameId,
                                   int userProfileId, Integer reviewId) throws DataException, EntityNotFoundException {
        try {
            Game g = gameRepo.findById(gameId)
                    .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));

            UserProfile up = userProfileRepo.findById(userProfileId)
                    .orElseThrow(() -> new EntityNotFoundException(UserProfile.class, userProfileId));

            if(reviewId!=null) {
                Review r = reviewRepo.findById(reviewId)
                        .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));
                userGame.setReview(r);
            }

            userGame.setUserProfile(up);
            userGame.setGame(g);
            return userGameRepo.save(userGame);
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: failed to update game", persistenceException);
        }
    }

    @Override
    public boolean deleteUseGame(UserGame userGame) throws DataException, EntityNotFoundException {
        try {
            if (!userGameRepo.existsById(userGame.getUserGameId())) {
                throw new EntityNotFoundException(UserGame.class, userGame.getUserGameId());
            }
            userGameRepo.delete(userGame);
            return true;
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: failed to delete game", persistenceException);
        }
    }

    @Override
    public int countOwnedUserGames() throws DataException {
        try {
            return ownedGamesRepo.findById(1)
                    .map(UserGamesOwnedCount::getOwnedCount)
                    .orElse(0);
        } catch (PersistenceException persistenceException){
            throw new DataException("No owned games found", persistenceException);
        }
    }

    @Override
    public List<Top5Tags> getTop5Tags() throws DataException {
        try {
            return top5TagsRepo.findAll();
        } catch (PersistenceException persistenceException){
            throw new DataException("No tags found", persistenceException);
        }
    }

    @Override
    public int countGamesPlaying() throws DataException {
        try {
            return gamesPlayingRepo.findById(1)
                    .map(UserGamesPlayingCount::getPlayingCount)
                    .orElse(0);
        } catch (PersistenceException persistenceException){
            throw new DataException("Cannot find any games with status: playing", persistenceException);
        }
    }

    @Override
    public int countGamesBeaten() throws DataException {
        try {
            return gamesBeatenRepo.findById(1)
                    .map(UserGamesBeatenCount::getBeatenCount)
                    .orElse(0);
        } catch (PersistenceException persistenceException){
            throw new DataException("Cannot find any games with status: beaten", persistenceException);
        }
    }

    @Override
    public int countUserReviews() throws DataException {
        try {
            return reviewCountRepo.findById(1)
                    .map(UserReviewsCount::getReviewsCount)
                    .orElse(0);
        } catch (PersistenceException persistenceException){
            throw new DataException("Cannot find any reviews", persistenceException);
        }
    }

    @Override
    public List<UserGame> findUserGamesByStatus(String status) throws DataException {
        try {
            return userGameRepo.findUserGameByStatus(status);
        } catch (PersistenceException persistenceException){
            throw new DataException("Cannot find games with status "+status, persistenceException);
        }
    }

    @Override
    public List<UserGame> findRecentlyBeaten() throws DataException {
        try {
            return userGameRepo.findUserGamesByCompletionDate();
        } catch (PersistenceException persistenceException){
            throw new DataException("Cannot find recently beaten", persistenceException);
        }
    }

    @Override
    public UserGame findUserGameByReviewId(int reviewId) throws DataException, EntityNotFoundException {
        try {
            if(!reviewRepo.existsById(reviewId)) {
                throw new EntityNotFoundException(Review.class, reviewId);
            }

            return userGameRepo.findByReviewId(reviewId)
                    .orElseThrow(() -> new DataException("Cannot find user game by review id "+reviewId));
        } catch (PersistenceException persistenceException){
            throw new DataException("Cannot find user game by review id "+reviewId, persistenceException);
        }
    }
}
