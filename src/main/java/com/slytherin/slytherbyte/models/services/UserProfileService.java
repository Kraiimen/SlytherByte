package com.slytherin.slytherbyte.models.services;

import com.slytherin.slytherbyte.models.entities.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    List<UserProfile> findAllUserProfiles();
    UserProfile createUserProfile(UserProfile userProfile);
    Optional<UserProfile> findUserProfileById(int userProfileId);
    boolean updateUserProfile(UserProfile userProfile);
}
