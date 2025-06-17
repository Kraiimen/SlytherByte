package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.DeveloperDto;
import com.slytherin.slytherbyte.models.entities.Developer;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.developer.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
@CrossOrigin(origins = "*")
public class DeveloperController {
    private DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService){
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity<List<DeveloperDto>> findAllDeveloper() throws DataException {
        List<Developer> developers = developerService.findAllDeveloper();
        var dtos = developers.stream().map(DeveloperDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("by-game/{id}")
    public ResponseEntity<List<DeveloperDto>> searchDevelopersByGameId(@PathVariable Integer id) throws DataException {
        List<Developer> developers = developerService.findDeveloperByGameId(id);
        var dtos = developers.stream().map(DeveloperDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}
