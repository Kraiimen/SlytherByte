package com.slytherin.slytherbyte.models.repositories.useraccount;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserAccountRepository extends JpaRepository<UserAccount, Integer> {
    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findByUsernameAccount(String username);
}
