package com.slytherin.slytherbyte.models.repos;

import com.slytherin.slytherbyte.models.entities.UserProfile;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepo extends JpaRepository<UserProfile, Integer> {

}
