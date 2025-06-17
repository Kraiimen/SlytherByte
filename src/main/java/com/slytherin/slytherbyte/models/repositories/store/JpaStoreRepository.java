package com.slytherin.slytherbyte.models.repositories.store;

import com.slytherin.slytherbyte.models.entities.Platform;
import com.slytherin.slytherbyte.models.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaStoreRepository extends JpaRepository<Store, Integer> {
    @Query("SELECT s FROM Store s JOIN s.games g WHERE g.gameId = :id")
    List<Store> getStoresByGameId(int id);
}
