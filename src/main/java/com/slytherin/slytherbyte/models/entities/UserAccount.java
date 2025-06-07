package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_account_id")
    private int userAccountId;

    private String username;

    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;


    public UserAccount(){}

    public UserAccount(int userAccountId, String username, String email,
                       String password, UserProfile userProfile){
        this.userAccountId=userAccountId;
        this.username=username;
        this.email=email;
        this.password=password;
        this.userProfile=userProfile;
    }
}
