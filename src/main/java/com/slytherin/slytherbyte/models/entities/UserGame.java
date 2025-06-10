package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_game")
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_game_id")
    private int userGameId;

    private String status;

    @Column(name="is_owned")
    private boolean isOwned;

    @OneToOne
    @JoinColumn(name="review_id")
    private Review review;

    @Column(name="completion_date")
    private LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    public UserGame(){}

    public UserGame(int userGameId, String status, boolean isOwned, Review review, LocalDate completionDate, Game game) {
        this.userGameId = userGameId;
        this.status = status;
        this.isOwned = isOwned;
        this.review = review;
        this.completionDate = completionDate;
        this.game = game;
    }

    public int getUserGameId() {
        return userGameId;
    }

    public String getStatus() {
        return status;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public Review getReview() {
        return review;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public Game getGame() {
        return game;
    }
}
