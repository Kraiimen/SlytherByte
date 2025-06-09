package com.slytherin.slytherbyte.models.services.franchise;

import com.slytherin.slytherbyte.models.entities.Franchise;
import com.slytherin.slytherbyte.models.repositories.JpaFranchiseRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class JpaFranchiseService implements FranchiseService{
    private JpaFranchiseRepository franchiseRepo;
    public JpaFranchiseService(JpaFranchiseRepository franchiseRepo){
        this.franchiseRepo = franchiseRepo;
    }
}
