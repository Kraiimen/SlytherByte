package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.StoreDto;
import com.slytherin.slytherbyte.models.entities.Store;
import com.slytherin.slytherbyte.models.services.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<List<StoreDto>> findAllStores(){
        List<Store> stores = storeService.findAllStores();
        var dtos = stores.stream().map(StoreDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}
