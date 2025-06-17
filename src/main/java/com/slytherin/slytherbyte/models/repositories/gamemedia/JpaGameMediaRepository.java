package com.slytherin.slytherbyte.models.repositories.gamemedia;

import com.slytherin.slytherbyte.models.entities.GameMedia;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaGameMediaRepository extends JpaRepository<GameMedia, Integer> {
    @Query("SELECT gm FROM GameMedia gm JOIN gm.game g WHERE g.gameId = :id AND gm.isTrailer = true")
    GameMedia getGameTrailers(int id) throws DataException;

    @Query("SELECT gm FROM GameMedia gm JOIN gm.game g WHERE g.gameId = :id AND gm.isTrailer = false")
    List<GameMedia> getMediaByGameId(int id) throws DataException;
}
