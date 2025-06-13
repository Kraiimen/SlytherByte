package com.slytherin.slytherbyte.models.repositories.views;

import com.slytherin.slytherbyte.models.entities.views.stats.OwnedUserGamesCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOwnedGamesRepository extends JpaRepository <OwnedUserGamesCount, Integer> {
}
