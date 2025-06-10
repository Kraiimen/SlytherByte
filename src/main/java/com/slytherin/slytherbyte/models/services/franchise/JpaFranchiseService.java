package com.slytherin.slytherbyte.models.services.franchise;

import com.slytherin.slytherbyte.models.repositories.franchise.JpaFranchiseRepository;
import org.springframework.stereotype.Service;

@Service
public class JpaFranchiseService implements FranchiseService{
    private JpaFranchiseRepository franchiseRepo;
    public JpaFranchiseService(JpaFranchiseRepository franchiseRepo){
        this.franchiseRepo = franchiseRepo;
    }
}
