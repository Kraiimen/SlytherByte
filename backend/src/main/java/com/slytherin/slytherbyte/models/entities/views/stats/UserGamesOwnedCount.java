package com.slytherin.slytherbyte.models.entities.views.stats;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="owned_games_count")
public class UserGamesOwnedCount {
    @Id
    @Column
    private int id;

    @Column(name="owned_count")
    private int ownedCount;

    public UserGamesOwnedCount(){}

    public UserGamesOwnedCount(int id, int ownedCount) {
        this.id = id;
        this.ownedCount = ownedCount;
    }

    public int getId() {
        return id;
    }

    public int getOwnedCount() {
        return ownedCount;
    }
}
