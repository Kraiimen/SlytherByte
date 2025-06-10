package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private  int reviewId;

    private String title;

    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne
    @JoinColumn(name="user_game_id")
    private UserGame userGame;

    public Review(){}

    public Review(int reviewId, String title, LocalDate date, UserProfile userProfile, Game game){
        this.reviewId = reviewId;
        this.title = title;
        this.date = date;
        this.userProfile = userProfile;
        this.game = game;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public Game getGame() {
        return game;
    }
}
