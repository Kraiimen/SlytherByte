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

    private String description;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne(mappedBy = "review")
    private UserGame userGame;

    public Review(){}

    public Review(int reviewId, String title, String description, LocalDate date, UserProfile userProfile, Game game){
        this.reviewId = reviewId;
        this.title = title;
        this.description = description;
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

    public String getDescription(){
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile){
        this.userProfile = userProfile;
    }

    public Game getGame() {
        return game;
    }
    public void setGame(Game game){
        this.game = game;
    }
}
