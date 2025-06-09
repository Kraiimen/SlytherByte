package com.slytherin.slytherbyte.models.services.game;

import com.slytherin.slytherbyte.models.entities.*;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Optional<Game> findGameById(int id);
    List<Game> findGameByFilters(GameFilterCriteria filters);
    boolean saveGame(Game newGame, int franchiseId);
    boolean updateGame(Game updatingGame, int franchiseId);
    boolean deleteGame(int id);
}
