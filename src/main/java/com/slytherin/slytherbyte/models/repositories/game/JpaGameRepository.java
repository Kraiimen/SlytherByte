package com.slytherin.slytherbyte.models.repositories.game;

import com.slytherin.slytherbyte.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGameRepository extends JpaRepository<Game, Integer>, JpaGameRepositoryCustom {
}
