package com.slytherin.slytherbyte.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private String summary;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;

    public Game() {
    }

    public Game(int gameId, String coverImageUrl, LocalDate releaseDate, String summary, Franchise franchise) {
        this.gameId = gameId;
        this.coverImageUrl = coverImageUrl;
        this.releaseDate = releaseDate;
        this.summary = summary;
        this.franchise = franchise;
    }
}
