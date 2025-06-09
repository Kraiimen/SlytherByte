package com.slytherin.slytherbyte.restcontrollers;

import com.slytherin.slytherbyte.dtos.GameDto;
import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;
import com.slytherin.slytherbyte.models.services.StoreService;
import org.aspectj.apache.bcel.classfile.Module;
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
    private StoreService storeService;

    @Autowired
    public GameRestController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> findGameById(@PathVariable Integer id){
        Optional<Game> optionalGame = storeService.findGameById(id);
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
        List<Game> games = storeService.findGameByFilters(filters);
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto){
        Game game = gameDto.toEntity();
        storeService.saveGame(game);
        GameDto savedGame = GameDto.toDto(game);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(game.getGameId())
                .toUri();
        return ResponseEntity.created(location).body(savedGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody GameDto gameDto){
        if(id != gameDto.toEntity().getGameId()){
            return ResponseEntity.badRequest().body("l'id del path de del dto non corrispondono");
        }
        Optional<Game> op = storeService.findGameById(gameDto.toEntity().getGameId());
        if(op.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        storeService.updateGame(gameDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        boolean deleted = storeService.deleteGame(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
