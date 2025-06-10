package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserGameRepository extends JpaRepository <UserGame, Integer> {

}
