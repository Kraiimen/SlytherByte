package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserProfileRepository extends JpaRepository<UserProfile, Integer> {

}
