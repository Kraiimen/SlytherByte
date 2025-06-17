package com.slytherin.slytherbyte.models.services.platform;

import com.slytherin.slytherbyte.models.entities.Platform;
import com.slytherin.slytherbyte.models.exceptions.DataException;

import java.util.List;

public interface PlatformService {
    List<Platform> findAllPlatforms() throws DataException;
    List<Platform> findPlatformsByGameId(int id) throws DataException;
}
