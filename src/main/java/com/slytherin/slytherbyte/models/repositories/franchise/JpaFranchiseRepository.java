package com.slytherin.slytherbyte.models.repositories.franchise;

import com.slytherin.slytherbyte.models.entities.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFranchiseRepository extends JpaRepository<Franchise, Integer> {
}
