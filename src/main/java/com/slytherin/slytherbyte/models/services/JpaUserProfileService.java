package com.slytherin.slytherbyte.models.services;

import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.repos.UserProfileRepo;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserProfileService implements UserProfileService {
    private UserProfileRepo userProfileRepo;

    public JpaUserProfileService(UserProfileRepo userProfileRepo){
        this.userProfileRepo=userProfileRepo;
    }

    @Override
    public List<UserProfile> findAllUserProfiles() {
        return userProfileRepo.findAll();
    }

    @Override
    public Optional<UserProfile> findUserProfileById(int userProfileId) {
        return userProfileRepo.findById(userProfileId);
    }

    @Override
    @Transactional
    public UserProfile createUserProfile(UserProfile userProfile) {
        try{
            userProfileRepo.save(userProfile);
            return userProfile;
        } catch (PersistenceException persistenceException){
            throw new DataIntegrityViolationException("Error: failed to create user profile", persistenceException);
        }
    }

    @Override
    @Transactional
    public boolean updateUserProfile(UserProfile userProfile) {
        try {
            Optional<UserProfile> up=userProfileRepo.findById(userProfile.getUserProfileId());
            if(up.isEmpty()){
                return false;
            }
            userProfileRepo.save(userProfile);
            return true;
        } catch(PersistenceException persistenceException){
            throw new DataIntegrityViolationException("Error: failed to update user profile", persistenceException);
        }
    }
}
