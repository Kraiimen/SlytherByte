package com.slytherin.slytherbyte.models.repositories.platform;

import com.slytherin.slytherbyte.models.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPlatformRepository extends JpaRepository<Platform, Integer> {
    @Query("SELECT p FROM Platform p JOIN p.games g WHERE g.gameId = :id")
    List<Platform> getPlatformsByGameId(int id);

}
