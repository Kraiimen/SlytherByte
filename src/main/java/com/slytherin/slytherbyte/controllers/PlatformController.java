package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.PlatformDto;
import com.slytherin.slytherbyte.models.entities.Platform;
import com.slytherin.slytherbyte.models.services.platform.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/platforms")
@CrossOrigin(origins = "*")
public class PlatformController {
    private PlatformService platformService;

    @Autowired
    public PlatformController(PlatformService platformService){
        this.platformService = platformService;
    }

    @GetMapping
    ResponseEntity<List<PlatformDto>> findAllPlatforms(){
        List<Platform> platforms = platformService.findAllPlatforms();
        var dtos = platforms.stream().map(PlatformDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }
}
