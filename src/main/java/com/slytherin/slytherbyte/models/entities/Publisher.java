package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private int publisherId;

    private String name;

    public Publisher() {
    }

    public Publisher(int publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }
}
