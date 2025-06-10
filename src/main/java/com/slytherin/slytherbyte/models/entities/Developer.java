package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private int developerId;

    private String name;

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
