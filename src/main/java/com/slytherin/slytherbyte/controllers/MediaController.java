package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.MediaDto;
import com.slytherin.slytherbyte.models.entities.Media;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.media.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin("*")
public class MediaController {
    private MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/trailer/{id}")
    ResponseEntity<MediaDto> searchGameTrailer(@PathVariable Integer id) throws DataException {
        Media media = mediaService.findGameTrailers(id);
        var dto = MediaDto.toDto(media);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<MediaDto>> searchMediaByGameId(@PathVariable Integer id) throws DataException {
        List<Media> media = mediaService.findMediaByGameId(id);
        var dtos = media.stream().map(MediaDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}
