package com.slytherin.slytherbyte.models.repositories.language;

import com.slytherin.slytherbyte.models.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLanguageRepository extends JpaRepository<Language, Integer> {
}
