package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int languageId;

    private String name;

    @ManyToMany(mappedBy = "languages")
    List<Game> games = new ArrayList<>();

    public Language() {
    }

    public Language(int languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getName() {
        return name;
    }
}
