package com.slytherin.slytherbyte.models.repositories.game;

import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.repositories.searchcriteria.GameFilterCriteria;

import java.util.List;

public interface JpaGameRepositoryCustom {
    List<Game> searchGames(GameFilterCriteria filters);
}
