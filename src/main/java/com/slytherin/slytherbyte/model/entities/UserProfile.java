package com.slytherin.slytherbyte.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_profile_id")
    private int userProfileId;

    @Column(name="profile_name")
    private String profileName;

    private String bio;

    @Column(name="profile_pic_url")
    private String picUrl;

    @OneToOne(mappedBy = "userProfile")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "userProfile")
    private List<Collection> collections;

    @OneToMany(mappedBy = "userProfile")
    private List<Review> reviews;

    public UserProfile(){}

    public UserProfile(int userProfileId, String profileName, String bio, String picUrl){
        this.userProfileId=userProfileId;
        this.profileName=profileName;
        this.bio=bio;
        this.picUrl=picUrl;
    }
}
