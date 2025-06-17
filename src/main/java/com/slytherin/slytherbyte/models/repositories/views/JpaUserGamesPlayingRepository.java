package com.slytherin.slytherbyte.models.repositories.views;

import com.slytherin.slytherbyte.models.entities.views.stats.UserGamesPlayingCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserGamesPlayingRepository extends JpaRepository <UserGamesPlayingCount, Integer> {
}
