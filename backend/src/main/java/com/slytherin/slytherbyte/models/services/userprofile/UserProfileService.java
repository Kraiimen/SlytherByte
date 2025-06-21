package com.slytherin.slytherbyte.models.services.userprofile;

import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> findAllUserProfiles() throws DataException;
    UserProfile createUserProfile(UserProfile userProfile) throws DataException;
    UserProfile findUserProfileById(int userProfileId) throws DataException, EntityNotFoundException;
    UserProfile updateUserProfile(UserProfile userProfile) throws DataException, EntityNotFoundException;
    List<UserProfile> findAllUserProfilesByName(String profileName) throws DataException, EntityNotFoundException;
    List<UserProfile> findAllUserProfilesByUsername(String username) throws DataException, EntityNotFoundException;
}
