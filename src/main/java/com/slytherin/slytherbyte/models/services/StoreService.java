package com.slytherin.slytherbyte.models.services;

import com.slytherin.slytherbyte.dtos.GameDto;
import com.slytherin.slytherbyte.models.entities.*;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    Optional<Game> findGameById(int id);
    List<Game> findGameByFilters(GameFilterCriteria filters);
    Game saveGame(Game newGame);
    Game updateGame(GameDto updatingGame);
    boolean deleteGame(int id);
}
