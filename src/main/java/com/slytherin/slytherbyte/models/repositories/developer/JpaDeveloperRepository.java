package com.slytherin.slytherbyte.models.repositories.developer;

import com.slytherin.slytherbyte.models.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeveloperRepository extends JpaRepository<Developer, Integer> {
}
