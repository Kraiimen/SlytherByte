package com.slytherin.slytherbyte.models.services.platform;

import com.slytherin.slytherbyte.models.entities.Platform;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.platform.JpaPlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaPlatformService implements PlatformService{
    private JpaPlatformRepository platformRepo;

    @Autowired
    public JpaPlatformService(JpaPlatformRepository platformRepo){
        this.platformRepo = platformRepo;
    }

    @Override
    public List<Platform> findAllPlatforms() throws DataException{
        return platformRepo.findAll();
    }

    @Override
    public List<Platform> findPlatformsByGameId(int id) throws DataException {
        return platformRepo.getPlatformsByGameId(id);
    }
}
