package com.slytherin.slytherbyte.models.repositories.store;

import com.slytherin.slytherbyte.models.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStoreRepository extends JpaRepository<Store, Integer> {
}
