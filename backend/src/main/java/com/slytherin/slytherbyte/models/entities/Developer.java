package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private int developerId;

    private String name;

    @ManyToMany(mappedBy = "developers")
    List<Game> games = new ArrayList<>();

    public Developer() {
    }

    public Developer(int developerId, String name) {
        this.developerId = developerId;
        this.name = name;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public String getName() {
        return name;
    }
}
