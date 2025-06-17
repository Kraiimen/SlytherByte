package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.StoreDto;
import com.slytherin.slytherbyte.models.entities.Store;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin(origins = "*")

public class StoreController {
    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping
    ResponseEntity<List<StoreDto>> findAllStores() throws DataException {
        List<Store> stores = storeService.findAllStores();
        var dtos = stores.stream().map(StoreDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/by-game/{id}")
    ResponseEntity<List<StoreDto>> searchStoresByGameId(@PathVariable Integer id) throws DataException {
        List<Store> stores = storeService.findStoresByGameId(id);
        var dtos = stores.stream().map(StoreDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}
