package com.slytherin.slytherbyte.models.services;

import com.slytherin.slytherbyte.dtos.GameDto;
import com.slytherin.slytherbyte.models.entities.*;
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
public class JpaStoreService implements StoreService{
    private JpaGameRepository gameRepo;

    public JpaStoreService(JpaGameRepository gameRepo){
        this.gameRepo = gameRepo;
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
    public Game saveGame(Game newGame) {
        return gameRepo.save((newGame));
    }

    @Override
    @Transactional
    public Game updateGame(GameDto gameDto) {
        int id = gameDto.gameId();
        Game updatedGame = gameRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Il gioco non è stato trovato"));
        updatedGame.setCoverImageUrl(gameDto.coverImageUrl());
        updatedGame.setReleaseDate(gameDto.realeaseDate());
        updatedGame.setSummary(gameDto.summary());
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