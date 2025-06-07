package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "franchise")
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    private int franchiseId;

    private String name;

    @OneToMany(mappedBy = "franchise")
    List<Game> games = new ArrayList<>();

    public Franchise() {
    }

    public Franchise(int franchiseId, String name) {
        this.franchiseId = franchiseId;
        this.name = name;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public String getName() {
        return name;
    }

    public List<Game> getGames() {
        return games;
    }
}
