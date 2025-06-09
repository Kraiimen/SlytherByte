package com.slytherin.slytherbyte.models.services.game;

import com.slytherin.slytherbyte.models.entities.*;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game findGameById(int id) throws DataException, EntityNotFoundException;
    List<Game> findGameByFilters(GameFilterCriteria filters) throws DataException;
    Game saveGame(Game newGame, int franchiseId) throws DataException, EntityNotFoundException;
    Game updateGame(Game updatingGame, int franchiseId) throws DataException, EntityNotFoundException;
    boolean deleteGame(int id) throws DataException;
}
