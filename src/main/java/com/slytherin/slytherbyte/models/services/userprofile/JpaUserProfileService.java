package com.slytherin.slytherbyte.models.services.userprofile;

import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.repositories.userprofile.JpaUserProfileRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUserProfileService implements UserProfileService {
    private JpaUserProfileRepository userProfileRepo;

    public JpaUserProfileService(JpaUserProfileRepository userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
    }

    @Override
    public List<UserProfile> findAllUserProfiles() throws DataException {
        try {
            return userProfileRepo.findAll();
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: no profiles found", persistenceException);
        }
    }

    @Override
    public UserProfile findUserProfileById(int userProfileId) throws DataException, EntityNotFoundException {
        try {
            Optional<UserProfile> oup = userProfileRepo.findById(userProfileId);
            if (oup.isEmpty()) {
                throw new EntityNotFoundException(UserProfile.class, userProfileId);
            }
            return oup.get();
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: no profiles with id " + userProfileId, persistenceException);
        }
    }

    @Override
    @Transactional
    public UserProfile createUserProfile(UserProfile userProfile) throws DataException {
        try {
            return userProfileRepo.save(userProfile);
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: failed to create user profile", persistenceException);
        }
    }

    @Override
    @Transactional
    public UserProfile updateUserProfile(UserProfile userProfile) throws DataException, EntityNotFoundException {
        try {
            if (!userProfileRepo.existsById(userProfile.getUserProfileId())) {
                throw new EntityNotFoundException(UserProfile.class, userProfile.getUserProfileId());
            }
            return userProfileRepo.save(userProfile);
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: failed to update user profile", persistenceException);
        }
    }

    @Override
    public List<UserProfile> findAllUserProfilesByName(String profileName) throws DataException, EntityNotFoundException {
        try {
            List<UserProfile> userProfilesFound= userProfileRepo.findAllByProfileNameLike(profileName+'%');
            if(userProfilesFound.isEmpty()){
                throw new EntityNotFoundException("No profiles with such criteria found");
            }
            return userProfilesFound;
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: no profiles found", persistenceException);
        }
    }

    @Override
    public List<UserProfile> findAllUserProfilesByUsername(String username) throws DataException, EntityNotFoundException {
        try {
            List<UserProfile> userProfilesFound= userProfileRepo.findAllByUserAccountUsernameLike(username+'%');
            if(userProfilesFound.isEmpty()){
                throw new EntityNotFoundException("No profiles with such criteria found");
            }
            return userProfilesFound;
        } catch (PersistenceException persistenceException) {
            throw new DataException("Error: no profiles found", persistenceException);
        }
    }
}
