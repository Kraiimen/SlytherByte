package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;

import java.util.List;

public interface JpaGameRepositoryCustom {
    List<Game> searchGames(GameFilterCriteria filters);
}
