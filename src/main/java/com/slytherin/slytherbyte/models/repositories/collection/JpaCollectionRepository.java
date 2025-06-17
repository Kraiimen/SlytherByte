package com.slytherin.slytherbyte.models.repositories.collection;

import com.slytherin.slytherbyte.models.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCollectionRepository extends JpaRepository<Collection, Integer> {
}
