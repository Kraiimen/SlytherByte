package com.slytherin.slytherbyte.models.services.usergame;

import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.entities.UserGame;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.repositories.JpaUserGameRepository;
import com.slytherin.slytherbyte.models.repositories.game.JpaGameRepository;
import com.slytherin.slytherbyte.models.repositories.userprofile.JpaUserProfileRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserGameService implements UserGameService {
    private JpaUserGameRepository userGameRepo;
    private JpaGameRepository gameRepo;
    private JpaUserProfileRepository userProfileRepo;

    public JpaUserGameService(JpaUserGameRepository userGameRepo, JpaGameRepository gameRepo,
                              JpaUserProfileRepository userProfileRepo) {
        this.userGameRepo = userGameRepo;
        this.gameRepo = gameRepo;
        this.userProfileRepo = userProfileRepo;
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
    public UserGame createUserGame(UserGame userGame, int gameId, int userProfileId) throws DataException, EntityNotFoundException {
        try {
            Game g = gameRepo.findById(gameId)
                    .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));

            UserProfile up = userProfileRepo.findById(userProfileId)
                    .orElseThrow((() -> new EntityNotFoundException(UserProfile.class, userProfileId)));

            userGame.setUserProfile(up);
            userGame.setGame(g);
           return userGameRepo.save(userGame);
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: failed to add game to user profile", persistenceException);
        }
    }

    @Override
    public UserGame updateUserGame(UserGame userGame) throws DataException, EntityNotFoundException {
        try {
            if (!userGameRepo.existsById(userGame.getUserGameId())) {
                throw new EntityNotFoundException(UserGame.class, userGame.getUserGameId());
            }
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
}
