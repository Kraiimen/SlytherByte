package com.slytherin.slytherbyte.models.repositories.publisher;

import com.slytherin.slytherbyte.models.entities.Platform;
import com.slytherin.slytherbyte.models.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPublisherRepository extends JpaRepository<Publisher, Integer> {
    @Query("SELECT p FROM Publisher p JOIN p.games g WHERE g.gameId = :id")
    List<Publisher> getPublishersByGameId(int id);
}
