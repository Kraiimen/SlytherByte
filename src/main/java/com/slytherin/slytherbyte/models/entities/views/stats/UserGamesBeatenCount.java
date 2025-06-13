package com.slytherin.slytherbyte.models.entities.views.stats;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="games_beaten_count")
public class UserGamesBeatenCount {
    @Id
    @Column(name="id")
    private int beatenCountId;

    @Column(name="beaten_count")
    private int beatenCount;

    public UserGamesBeatenCount(){};

    public UserGamesBeatenCount(int beatenCountId, int beatenCount){
        this.beatenCountId=beatenCountId;
        this.beatenCount=beatenCount;
    }

    public int getBeatenCountId() {
        return beatenCountId;
    }

    public int getBeatenCount() {
        return beatenCount;
    }
}
