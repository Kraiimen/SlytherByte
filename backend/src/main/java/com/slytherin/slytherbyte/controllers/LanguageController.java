package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.LanguageDto;
import com.slytherin.slytherbyte.models.entities.Language;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.language.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@CrossOrigin(origins = "*")
public class LanguageController {
    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService){
        this.languageService = languageService;
    }

    @GetMapping
    ResponseEntity<List<LanguageDto>> findAll() throws DataException {
        List<Language> languages = languageService.findAllLanguages();
        var dtos = languages.stream().map(LanguageDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/by-game/{id}")
    ResponseEntity<List<LanguageDto>> searchLanguagesBYGameId(@PathVariable Integer id) throws DataException {
        List<Language> languages = languageService.findLanguagesByGameId(id);
        var dtos = languages.stream().map(LanguageDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }
}
