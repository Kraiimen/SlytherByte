package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAccountRepository extends JpaRepository <UserAccount, Integer> {
}
