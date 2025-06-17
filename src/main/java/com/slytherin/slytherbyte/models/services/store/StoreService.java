package com.slytherin.slytherbyte.models.services.store;

import com.slytherin.slytherbyte.models.entities.Store;
import com.slytherin.slytherbyte.models.exceptions.DataException;

import java.util.List;

public interface StoreService {
    List<Store> findAllStores() throws DataException;
    List<Store> findStoresByGameId(int id) throws DataException;
}
