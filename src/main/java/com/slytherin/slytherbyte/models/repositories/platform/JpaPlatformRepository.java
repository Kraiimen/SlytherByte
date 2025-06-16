package com.slytherin.slytherbyte.models.repositories.platform;

import com.slytherin.slytherbyte.models.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlatformRepository extends JpaRepository<Platform, Integer> {
}
