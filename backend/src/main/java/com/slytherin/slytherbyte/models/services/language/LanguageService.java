package com.slytherin.slytherbyte.models.services.language;

import com.slytherin.slytherbyte.models.entities.Language;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.language.JpaLanguageRepository;

import java.util.List;

public interface LanguageService {
    List<Language> findAllLanguages() throws DataException;
    List<Language> findLanguagesByGameId(int id) throws DataException;
}
