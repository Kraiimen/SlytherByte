package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="collection_id")
    private int collectionId;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    public Collection(){}

    public Collection(int collectionId, String name, String description, UserProfile userProfile){
        this.collectionId=collectionId;
        this.name=name;
        this.description=description;
        this.userProfile=userProfile;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
