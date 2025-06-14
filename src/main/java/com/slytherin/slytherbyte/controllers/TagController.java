package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.GameDto;
import com.slytherin.slytherbyte.dtos.TagDto;
import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.entities.Tag;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "*")
public class TagController {
    private TagService tagService;
    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> findAll() throws DataException {
        List<Tag> tags = tagService.findAllTags();
        var dtos = tags.stream().map(TagDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }
}
