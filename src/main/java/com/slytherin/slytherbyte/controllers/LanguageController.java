package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.LanguageDto;
import com.slytherin.slytherbyte.models.entities.Language;
import com.slytherin.slytherbyte.models.services.language.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<List<LanguageDto>> findAll(){
        List<Language> languages = languageService.findAllLanguages();
        var dtos = languages.stream().map(LanguageDto::toDto).toList();
        return ResponseEntity.ok(dtos);

    }
}
