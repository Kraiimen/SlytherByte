package com.slytherin.slytherbyte.models.entities.views.stats;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="games_playing_count")
public class UserGamesPlayingCount {
    @Id
    @Column(name="id")
    private int playingCountId;

    @Column(name="playing_count")
    private int playingCount;

    public UserGamesPlayingCount(){}

    public UserGamesPlayingCount(int playingCountId, int playingCount) {
        this.playingCountId = playingCountId;
        this.playingCount = playingCount;
    }

    public int getPlayingCountId() {
        return playingCountId;
    }

    public int getPlayingCount() {
        return playingCount;
    }

}
