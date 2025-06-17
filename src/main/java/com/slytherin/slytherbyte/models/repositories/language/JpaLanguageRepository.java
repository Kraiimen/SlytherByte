package com.slytherin.slytherbyte.models.repositories.language;

import com.slytherin.slytherbyte.models.entities.Language;
import com.slytherin.slytherbyte.models.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaLanguageRepository extends JpaRepository<Language, Integer> {
    @Query("SELECT l FROM Language l JOIN l.games g WHERE g.gameId = :id")
    List<Language> getLanguagesByGameId(int id);
}
