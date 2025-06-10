package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_account")
public class UserAccount implements UserDetails, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_id")
    private int userAccountId;

    private String username;

    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    private String authority;

    public UserAccount() {
    }

    public UserAccount(int userAccountId, String username, String email,
                       String password, UserProfile userProfile, String authority) {
        this.userAccountId = userAccountId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userProfile = userProfile;
        this.authority = authority;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
