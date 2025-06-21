package com.slytherin.slytherbyte.models.services.store;

import com.slytherin.slytherbyte.models.entities.Store;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.store.JpaStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaStoreService implements StoreService{
    private JpaStoreRepository storeRepo;

    @Autowired
    public JpaStoreService(JpaStoreRepository storeRepo) {
        this.storeRepo = storeRepo;
    }

    @Override
    public List<Store> findAllStores() throws DataException{
        return storeRepo.findAll();
    }

    @Override
    public List<Store> findStoresByGameId(int id) throws DataException {
        return storeRepo.getStoresByGameId(id);
    }
}
