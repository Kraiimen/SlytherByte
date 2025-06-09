package com.slytherin.slytherbyte.restcontrollers;

import com.slytherin.slytherbyte.dtos.GameDto;
import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;
import com.slytherin.slytherbyte.models.services.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Game> findGameById(@PathVariable Integer id){
        Optional<Game> optionalGame = gameService.findGameById(id);
        if(optionalGame.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(optionalGame.get());
        }
    }

    @GetMapping
    public ResponseEntity<List<Game>> findGameByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false)LocalDate releaseDate,
            @RequestParam(required = false)List<String> platforms,
            @RequestParam(required = false)List<String> languages,
            @RequestParam(required = false)List<String> tags,
            @RequestParam(required = false)List<String> stores,
            @RequestParam(required = false)List<String> publishers,
            @RequestParam(required = false, defaultValue = "false") boolean nameSorter,
            @RequestParam(required = false, defaultValue = "false") boolean dateSorter
            ){
        GameFilterCriteria filters = new GameFilterCriteria(title, releaseDate, platforms,
                                                            languages, tags, stores, publishers,
                                                            nameSorter, dateSorter);
        List<Game> games = gameService.findGameByFilters(filters);
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto){
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
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Game gameDto){
        if(id != gameDto.toEntity().getGameId()){
            return ResponseEntity.badRequest().body("l'id del path de del dto non corrispondono");
        }
        Optional<Game> op = gameService.findGameById(gameDto.toEntity().getGameId());
        if(op.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        gameService.updateGame(game);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        boolean deleted = gameService.deleteGame(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
