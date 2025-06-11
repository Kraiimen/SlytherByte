package com.slytherin.slytherbyte.models.services.usergame;

import com.slytherin.slytherbyte.models.entities.UserGame;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;

import java.util.List;

public interface UserGameService {
    List<UserGame> findAllUserGames() throws DataException;
    UserGame findUserGameById(int userGameId) throws DataException, EntityNotFoundException;
    UserGame createUserGame(UserGame userGame, int gameId, int userProfileId) throws DataException, EntityNotFoundException;
    UserGame updateUserGame(UserGame userGame) throws DataException, EntityNotFoundException;
    boolean deleteUseGame(UserGame userGame) throws DataException, EntityNotFoundException;

}
