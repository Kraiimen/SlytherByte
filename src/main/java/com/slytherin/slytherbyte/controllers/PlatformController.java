package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.PlatformDto;
import com.slytherin.slytherbyte.models.entities.Platform;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.platform.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<List<PlatformDto>> findAllPlatforms() throws DataException {
        List<Platform> platforms = platformService.findAllPlatforms();
        var dtos = platforms.stream().map(PlatformDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/by-game/{id}")
    ResponseEntity<List<PlatformDto>> searchPlatformsByGameID(@PathVariable Integer id) throws DataException {
        List<Platform> platforms = platformService.findPlatformsByGameId(id);
        var dtos = platforms.stream().map(PlatformDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }
}
