package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.UserGameDto;
import com.slytherin.slytherbyte.models.entities.UserGame;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.services.usergame.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user-games")
public class UserGameController {
    private UserGameService userGameService;

    @Autowired
    public UserGameController(UserGameService userGameService) {
        this.userGameService = userGameService;
    }

    @GetMapping
    public ResponseEntity<List<UserGameDto>> getAll() throws DataException {
        List<UserGame> userGames = userGameService.findAllUserGames();
        List<UserGameDto> ugDto = userGames.stream().map(UserGameDto::toDto).toList();
        return ResponseEntity.ok(ugDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGameDto> getById(@PathVariable("id") int userGameId) throws DataException, EntityNotFoundException {
        UserGame ug = userGameService.findUserGameById(userGameId);
        UserGameDto ugDto = UserGameDto.toDto(ug);
        return ResponseEntity.ok(ugDto);
    }

    @PostMapping
    public ResponseEntity<UserGameDto> createUserGame(@RequestBody UserGameDto userGameDto) throws DataException, EntityNotFoundException {
        UserGame userGame = userGameDto.toEntity();
        UserGameDto ugDto = UserGameDto.toDto(userGameService.createUserGame(userGame, userGameDto.gameId(), userGameDto.userProfileId()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ugDto.userGameId())
                .toUri();

        return ResponseEntity.created(location).body(ugDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGameDto> updateById(@PathVariable("id") int userGameId, @RequestBody UserGameDto userGameDto) throws DataException, EntityNotFoundException {
        if (userGameDto.userGameId() != userGameId) {
            return ResponseEntity.badRequest().build();
        }

        UserGameDto ugDto = UserGameDto.toDto(userGameService.updateUserGame(userGameDto.toEntity()));
        return ResponseEntity.ok(ugDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int userGameId) throws DataException, EntityNotFoundException {
        UserGame ug = userGameService.findUserGameById(userGameId);
        userGameService.deleteUseGame(ug);
        return ResponseEntity.noContent().build();
    }

}
