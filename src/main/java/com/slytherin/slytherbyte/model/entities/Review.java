package com.slytherin.slytherbyte.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private  int reviewId;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Review(){}

    public Review(int reviewId, String title, String date, UserProfile userProfile, Game game){
        this.reviewId = reviewId;
        this.title = title;
        this.date = date;
        this.userProfile = userProfile;
        this.game = game;
    }
}
