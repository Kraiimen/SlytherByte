package com.slytherin.slytherbyte.models.repositories.developer;

import com.slytherin.slytherbyte.models.entities.Developer;
import com.slytherin.slytherbyte.models.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDeveloperRepository extends JpaRepository<Developer, Integer> {
    @Query("SELECT d FROM Developer d JOIN d.games g WHERE g.gameId = :id")
    List<Developer> getDevelopersByGameId(int id);
}
