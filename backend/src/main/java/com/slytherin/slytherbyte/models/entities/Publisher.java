package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private int publisherId;

    private String name;

    @ManyToMany(mappedBy = "publishers")
    List<Game> games = new ArrayList<>();

    public Publisher() {
    }

    public Publisher(int publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }
}
