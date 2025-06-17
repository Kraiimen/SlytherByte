package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.GameMediaDto;
import com.slytherin.slytherbyte.models.entities.GameMedia;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.services.gamemedia.GameMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/game-media")
public class GameMediaController {
    private GameMediaService gameMediaService;

    @Autowired
    public GameMediaController(GameMediaService gameMediaService) {
        this.gameMediaService = gameMediaService;
    }

    @GetMapping("/trailer/{id}")
    ResponseEntity<GameMediaDto> searchGameTrailer(@PathVariable Integer id) throws DataException {
        GameMedia gameMedia = gameMediaService.findGameTrailers(id);
        var dto = GameMediaDto.toDto(gameMedia);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<GameMediaDto>> searchMediaByGameId(@PathVariable Integer id) throws DataException {
        List<GameMedia> gameMedia = gameMediaService.findMediaByGameId(id);
        var dtos = gameMedia.stream().map(GameMediaDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

}
