package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaUserGameRepository extends JpaRepository <UserGame, Integer> {
    @Query("SELECT ug FROM UserGame ug WHERE LOWER(status)=LOWER(:status)")
    List<UserGame> findUserGameByStatus(@Param("status") String status);
}
