package com.slytherin.slytherbyte.models.services.usergame;

import com.slytherin.slytherbyte.models.entities.UserGame;
import com.slytherin.slytherbyte.models.entities.views.stats.Top5Tags;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;

import java.util.List;

public interface UserGameService {
    List<UserGame> findAllUserGames() throws DataException;
    UserGame findUserGameById(int userGameId) throws DataException, EntityNotFoundException;
    UserGame createUserGame(UserGame userGame, int gameId, int userProfileId, Integer reviewId) throws DataException, EntityNotFoundException;
    UserGame updateUserGame(UserGame userGame, int gameId, int userProfileId, Integer reviewId) throws DataException, EntityNotFoundException;
    boolean deleteUserGameById(int userGameId) throws DataException, EntityNotFoundException;
    int countOwnedUserGames() throws DataException;
    List<Top5Tags> getTop5Tags() throws DataException;
    int countGamesPlaying() throws DataException;
    int countGamesBeaten() throws DataException;
    int countUserReviews() throws DataException;
    List<UserGame> findUserGamesByStatus(String status) throws DataException;
    List<UserGame> findUserGamesByStatus(int userProfileId, String status) throws DataException;
    List<UserGame> findRecentlyBeatenByProfileId(int userProfileId) throws DataException;
    UserGame findUserGameByReviewId(int reviewId) throws DataException, EntityNotFoundException;
    List<UserGame> findAllByUserProfileId(int userProfileId) throws DataException, EntityNotFoundException;
}
