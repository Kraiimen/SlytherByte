package com.slytherin.slytherbyte.models.repositories.tag;

import com.slytherin.slytherbyte.models.entities.Platform;
import com.slytherin.slytherbyte.models.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaTagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT t FROM Tag t JOIN t.games g WHERE g.gameId = :id")
    List<Tag> getTagsByGameId(int id);
}
