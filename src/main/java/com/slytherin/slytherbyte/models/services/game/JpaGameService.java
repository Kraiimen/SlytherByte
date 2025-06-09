package com.slytherin.slytherbyte.models.services.game;

import com.slytherin.slytherbyte.models.entities.*;
import com.slytherin.slytherbyte.models.repositories.JpaFranchiseRepository;
import com.slytherin.slytherbyte.models.repositories.JpaGameRepository;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("jpa")
public class JpaGameService implements GameService {
    private JpaGameRepository gameRepo;
    private JpaFranchiseRepository franchiseRepo;

    public JpaGameService(JpaGameRepository gameRepo, JpaFranchiseRepository franchiseRepo){
        this.gameRepo = gameRepo;
        this.franchiseRepo = franchiseRepo;
    }


    @Override
    public Optional<Game> findGameById(int id) {
        return gameRepo.findById(id);
    }

    @Override
    public List<Game> findGameByFilters(GameFilterCriteria filters) {
        return gameRepo.searchGames(filters);
    }

    @Override
    @Transactional
    public boolean saveGame(Game newGame, int franchiseId) {
        if(!franchiseRepo.existsById(franchiseId)){
            return false;
        }
        gameRepo.save(newGame);
        return true;

    }

    @Override
    @Transactional
    public boolean updateGame(Game game, int franchiseId) {
        int id = game.getGameId();
        Game updatedGame = gameRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Il gioco non è stato trovato"));
        updatedGame.setCoverImageUrl(game.getCoverImageUrl());
        updatedGame.setReleaseDate(game.getReleaseDate());
        updatedGame.setSummary(game.getSummary());
        return gameRepo.save(updatedGame);
    }

    @Override
    @Transactional
    public boolean deleteGame(int id) {
        Optional<Game> og = gameRepo.findById(id);
        if(og.isPresent()){
            gameRepo.delete(og.get());
            return true;
        }else{
            return false;
        }
    }
}