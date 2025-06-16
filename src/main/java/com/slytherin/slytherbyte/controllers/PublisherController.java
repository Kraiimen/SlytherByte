package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.PublisherDto;
import com.slytherin.slytherbyte.models.entities.Publisher;
import com.slytherin.slytherbyte.models.services.publisher.JpaPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<List<PublisherDto>> findAllPlatforms(){
        List<Publisher> publishers = publisherService.findAllPublishers();
        var dtos = publishers.stream().map(PublisherDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}
