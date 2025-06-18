package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.UserGameDto;
import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.entities.UserGame;
import com.slytherin.slytherbyte.models.entities.views.stats.Top5Tags;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.services.usergame.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<UserGameDto>> getAll(@RequestParam(name = "status", required = false) String status) throws DataException {
        if (status != null && !status.isEmpty()) {
            List<UserGame> userGamesByStatus = userGameService.findUserGamesByStatus(status);
            List<UserGameDto> ugBySDto = userGamesByStatus.stream().map(UserGameDto::toDto).toList();
            return ResponseEntity.ok(ugBySDto);
        }
        List<UserGame> userGames = userGameService.findAllUserGames();
        List<UserGameDto> ugDto = userGames.stream().map(UserGameDto::toDto).toList();
        return ResponseEntity.ok(ugDto);
    }

    @GetMapping("/logged-user")
    public ResponseEntity<List<UserGameDto>> getAllForLoggedUser(@AuthenticationPrincipal UserAccount ua, @RequestParam(name = "status", required = false) String status) throws DataException, EntityNotFoundException {
        if (status != null && !status.isEmpty()) {
            List<UserGame> userGamesByStatus = userGameService.findUserGamesByStatus(ua.getUserProfile().getUserProfileId(), status);
            List<UserGameDto> ugBySDto = userGamesByStatus.stream().map(UserGameDto::toDto).toList();
            return ResponseEntity.ok(ugBySDto);
        }

        List<UserGame> userGames = userGameService.findAllByUserProfileId(ua.getUserProfile().getUserProfileId());
        List<UserGameDto> ugDto = userGames.stream().map(UserGameDto::toDto).toList();
        return ResponseEntity.ok(ugDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGameDto> getById(@PathVariable("id") int userGameId) throws DataException, EntityNotFoundException {
        UserGame ug = userGameService.findUserGameById(userGameId);
        UserGameDto ugDto = UserGameDto.toDto(ug);
        return ResponseEntity.ok(ugDto);
    }

    @GetMapping("/by-review/{id}")
    public ResponseEntity<UserGameDto> getByReviewId(@PathVariable("id") int reviewId) throws DataException, EntityNotFoundException {
        UserGame ug = userGameService.findUserGameByReviewId(reviewId);
        UserGameDto ugDto = UserGameDto.toDto(ug);
        return ResponseEntity.ok(ugDto);
    }

    @PostMapping
    public ResponseEntity<UserGameDto> createUserGame(@RequestBody UserGameDto userGameDto) throws DataException, EntityNotFoundException {
        UserGame userGame = userGameDto.toEntity();
        UserGameDto ugDto = UserGameDto.toDto(userGameService.createUserGame(userGame, userGameDto.gameId(), userGameDto.userProfileId(), userGameDto.reviewId()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ugDto.userGameId())
                .toUri();

        return ResponseEntity.created(location).body(ugDto);
    }

    @PostMapping("/logged-user")
    public ResponseEntity<UserGameDto> createUserGameForLoggedUser(@AuthenticationPrincipal UserAccount ua, @RequestBody UserGameDto userGameDto) throws DataException, EntityNotFoundException {
        UserGame userGame = userGameDto.toEntity();
        UserGameDto ugDto = UserGameDto.toDto(userGameService.createUserGame(userGame, userGameDto.gameId(), ua.getUserProfile().getUserProfileId(), userGameDto.reviewId()));

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
            System.out.println("sta minchia");
            return ResponseEntity.badRequest().build();
        }
        UserGame userGame = userGameDto.toEntity();
        UserGameDto ugDto = UserGameDto.toDto(userGameService.updateUserGame(userGame, userGameDto.gameId(), userGameDto.userProfileId(),
                userGameDto.reviewId()));
        return ResponseEntity.ok(ugDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int userGameId) throws DataException, EntityNotFoundException {
        userGameService.deleteUserGameById(userGameId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owned/count")
    public ResponseEntity<Integer> getOwnedGamesCount() throws DataException {
        int count = userGameService.countOwnedUserGames();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/tags/top5")
    public ResponseEntity<List<Top5Tags>> getTop5Tags() throws DataException {
        List<Top5Tags> tags = userGameService.getTop5Tags();
        return ResponseEntity.ok(tags);

    }

    @GetMapping("/playing")
    public ResponseEntity<Integer> getPlayingGames(@AuthenticationPrincipal UserAccount ua) throws DataException {
        Integer count = userGameService.countGamesPlaying(ua.getUserProfile().getUserProfileId());
        return ResponseEntity.ok(count);
    }

    @GetMapping("/beaten")
    public ResponseEntity<Integer> getBeatenGames(@AuthenticationPrincipal UserAccount ua) throws DataException {
        Integer count = userGameService.countGamesBeaten(ua.getUserProfile().getUserProfileId());
        return ResponseEntity.ok(count);
    }

    @GetMapping("/reviews/count")
    public ResponseEntity<Integer> getReviewsCount() throws DataException {
        Integer count = userGameService.countUserReviews();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/recently-beaten")
    public ResponseEntity<List<UserGameDto>> getRecentlyBeaten(@AuthenticationPrincipal UserAccount ua) throws DataException {
        List<UserGame> recentGames = userGameService.findRecentlyBeatenByProfileId(ua.getUserProfile().getUserProfileId());
        List<UserGameDto> ugDto = recentGames.stream().map(UserGameDto::toDto).toList();
        return ResponseEntity.ok(ugDto);
    }

}
