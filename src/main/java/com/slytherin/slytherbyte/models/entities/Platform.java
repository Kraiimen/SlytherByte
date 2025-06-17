package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "platform")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="platform_id")
    private int platformId;

    private String name;

    @ManyToMany(mappedBy = "platforms")
    List<Game> games = new ArrayList<>();

    public Platform(){}

    public Platform(int platformId, String name){
        this.platformId=platformId;
        this.name=name;
    }

    public int getPlatformId() {
        return platformId;
    }

    public String getName() {
        return name;
    }
}
