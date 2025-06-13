package com.slytherin.slytherbyte.models.services.useraccount;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.repositories.useraccount.JpaUserAccountRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaUserAccountService implements UserAccountService{
    private JpaUserAccountRepository userAccountRepo;

    @Autowired
    public JpaUserAccountService(JpaUserAccountRepository userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    @Override
    public UserAccount findById(int id) throws DataException, EntityNotFoundException {
        try {
            return userAccountRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(UserAccount.class, id));
        } catch (PersistenceException pe) {
            throw new DataException("Failed to find user account with id: " + id, pe);
        }
    }
}
