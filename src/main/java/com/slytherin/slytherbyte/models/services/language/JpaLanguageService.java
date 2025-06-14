package com.slytherin.slytherbyte.models.services.language;

import com.slytherin.slytherbyte.models.entities.Language;
import com.slytherin.slytherbyte.models.repositories.language.JpaLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaLanguageService implements LanguageService{
    private JpaLanguageRepository languageRepo;

    @Autowired
    public JpaLanguageService(JpaLanguageRepository languageRepo) {
        this.languageRepo = languageRepo;
    }

    @Override
    public List<Language> findAllLanguages() {
        return languageRepo.findAll();
    }
}
