package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int languageId;

    private String name;

    public Language() {
    }

    public Language(int languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }
}
