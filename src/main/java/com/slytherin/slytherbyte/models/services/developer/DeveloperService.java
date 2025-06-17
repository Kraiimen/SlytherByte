package com.slytherin.slytherbyte.models.services.developer;

import com.slytherin.slytherbyte.models.entities.Developer;
import com.slytherin.slytherbyte.models.exceptions.DataException;

import java.util.List;

public interface DeveloperService {
    List<Developer> findAllDeveloper() throws DataException;
    List<Developer> findDeveloperByGameId(int id) throws DataException;
}
