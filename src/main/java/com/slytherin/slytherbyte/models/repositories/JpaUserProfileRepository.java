package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaUserProfileRepository extends JpaRepository<UserProfile, Integer> {
    @Query("SELECT u FROM UserProfile u WHERE LOWER(profileName) LIKE LOWER(:profileName)")
    List<UserProfile> findAllByProfileNameLike(@Param("profileName") String profileName);

    @Query("SELECT u FROM UserAccount u WHERE LOWER(username) LIKE LOWER(:username)")
    List<UserProfile> findAllByUserAccountUsernameLike(@Param("username") String username);
}
