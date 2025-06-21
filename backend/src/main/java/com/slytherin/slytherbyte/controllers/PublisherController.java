package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.PublisherDto;
import com.slytherin.slytherbyte.models.entities.Publisher;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.publisher.JpaPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@CrossOrigin(origins = "*")
public class PublisherController {
    private JpaPublisherService publisherService;

    @Autowired
    public PublisherController(JpaPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    ResponseEntity<List<PublisherDto>> findAllPlatforms() throws DataException {
        List<Publisher> publishers = publisherService.findAllPublishers();
        var dtos = publishers.stream().map(PublisherDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/by-game/{id}")
    ResponseEntity<List<PublisherDto>> searchPlatformsByGameId(@PathVariable Integer id) throws DataException {
        List<Publisher> publishers = publisherService.findPublishersByGameId(id);
        var dtos = publishers.stream().map(PublisherDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}
