package com.slytherin.slytherbyte.models.services.language;

import com.slytherin.slytherbyte.models.entities.Language;
import com.slytherin.slytherbyte.models.repositories.language.JpaLanguageRepository;

import java.util.List;

public interface LanguageService {
    List<Language> findAllLanguages();
}
