package com.slytherin.slytherbyte.models.services;

import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    List<UserProfile> findAllUserProfiles() throws DataException;
    UserProfile createUserProfile(UserProfile userProfile, int userAccountId) throws DataException, EntityNotFoundException;
    UserProfile findUserProfileById(int userProfileId) throws DataException, EntityNotFoundException;
    UserProfile updateUserProfile(UserProfile userProfile, int userAccountId) throws DataException, EntityNotFoundException;
}
