package com.slytherin.slytherbyte.models.repositories.media;

import com.slytherin.slytherbyte.models.entities.Media;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaMediaRepository extends JpaRepository<Media, Integer> {
    @Query("SELECT m FROM Media m JOIN m.game g WHERE g.gameId = :id AND m.isTrailer = true")
    Media getGameTrailers(int id) throws DataException;

    @Query("SELECT m FROM Media m JOIN m.game g WHERE g.gameId = :id AND m.isTrailer = false")
    List<Media> getMediaByGameId(int id) throws DataException;
}
