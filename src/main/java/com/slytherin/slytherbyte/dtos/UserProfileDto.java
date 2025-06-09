package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.entities.UserProfile;

public record UserProfileDto(int userProfileId, String profileName, String bio, String profilePicUrl) {

    public static UserProfileDto toDto(UserProfile userProfile){
        return new UserProfileDto(userProfile.getUserProfileId(), userProfile.getProfileName(),
                userProfile.getBio(), userProfile.getProfilePicUrl());
    }

    public UserProfile toEntity(){
        return new UserProfile(this.userProfileId, this.profileName, this.bio, this.profilePicUrl);
    }
}
