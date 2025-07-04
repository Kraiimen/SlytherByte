package com.slytherin.slytherbyte.models.services.developer;

import com.slytherin.slytherbyte.models.entities.Developer;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.developer.JpaDeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaDeveloperService implements DeveloperService{
    private JpaDeveloperRepository developerRepo;

    @Autowired
    public JpaDeveloperService(JpaDeveloperRepository developerRepo) throws DataException{
        this.developerRepo = developerRepo;
    }

    @Override
    public List<Developer> findAllDeveloper() {
        return developerRepo.findAll();
    }

    @Override
    public List<Developer> findDeveloperByGameId(int id) throws DataException {
        return developerRepo.getDevelopersByGameId(id);
    }
}
