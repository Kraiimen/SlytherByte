package com.slytherin.slytherbyte.model.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="platform_id")
    private int platformId;

    private String name;

    public Platform(){}

    public Platform(int platformId, String name){
        this.platformId=platformId;
        this.name=name;
    }
}
