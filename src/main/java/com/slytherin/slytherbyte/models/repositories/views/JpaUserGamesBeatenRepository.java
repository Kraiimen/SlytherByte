package com.slytherin.slytherbyte.models.repositories.views;

import com.slytherin.slytherbyte.models.entities.views.stats.UserGamesBeatenCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserGamesBeatenRepository extends JpaRepository<UserGamesBeatenCount, Integer> {
}
