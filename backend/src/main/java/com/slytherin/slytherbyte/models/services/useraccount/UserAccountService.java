package com.slytherin.slytherbyte.models.services.useraccount;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;

public interface UserAccountService {
    UserAccount findById(int id) throws DataException, EntityNotFoundException;
}
