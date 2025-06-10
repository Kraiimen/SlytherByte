package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.GameDto;
import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.repositories.searchcriteria.GameFilterCriteria;
import com.slytherin.slytherbyte.models.services.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameRestController {
    private GameService gameService;

    @Autowired
    public GameRestController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> findGameById(@PathVariable Integer id) throws DataException, EntityNotFoundException {
        Game foundGame = gameService.findGameById(id);
        return ResponseEntity.ok(GameDto.toDto(foundGame));
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> findGames(@RequestBody(required = false) GameFilterCriteria filters) throws DataException {
        List<Game> games = null;

        if(filters != null) {
            games = gameService.findGameByFilters(filters);
        } else {
            games = gameService.findAllGames();
        }

        var dtos = games.stream().map(GameDto::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) throws DataException, EntityNotFoundException {
        Game game = gameDto.toEntity();
        gameService.saveGame(game, gameDto.franchiseId());
        GameDto savedGame = GameDto.toDto(game);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(game.getGameId())
                .toUri();
        return ResponseEntity.created(location).body(savedGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody GameDto gameDto) throws DataException, EntityNotFoundException {
        if(id != gameDto.gameId()){
            return ResponseEntity.badRequest().body("Path id and game id don't match");
        }
        gameService.updateGame(gameDto.toEntity(), gameDto.franchiseId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) throws DataException {
        boolean deleted = gameService.deleteGame(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
