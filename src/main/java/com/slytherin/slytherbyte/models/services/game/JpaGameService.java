package com.slytherin.slytherbyte.models.services.game;

import com.slytherin.slytherbyte.models.entities.*;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.repositories.JpaFranchiseRepository;
import com.slytherin.slytherbyte.models.repositories.JpaGameRepository;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaGameService implements GameService {
    private JpaGameRepository gameRepo;
    private JpaFranchiseRepository franchiseRepo;

    public JpaGameService(JpaGameRepository gameRepo, JpaFranchiseRepository franchiseRepo){
        this.gameRepo = gameRepo;
        this.franchiseRepo = franchiseRepo;
    }


    @Override
    public Game findGameById(int id) throws DataException, EntityNotFoundException {
        try{
            Optional<Game> og = gameRepo.findById(id);
            if(og.isEmpty()){
                throw new EntityNotFoundException(Game.class, id);
            }
            return og.get();
        }catch(PersistenceException pe){
            throw new DataException("Failed to find game with id: " + id, pe);
        }

    }

    @Override
    public List<Game> findAllGames() throws DataException {
        try {
            return gameRepo.findAll();
        } catch (PersistenceException pe) {
            throw new DataException("Failed to retrieve all games", pe);
        }
    }

    @Override
    public List<Game> findGameByFilters(GameFilterCriteria filters) throws DataException {
        try{
        return gameRepo.searchGames(filters);
        }catch(PersistenceException pe){
            throw new DataException("Failed to retrieve games by filter", pe);
        }
    }

    @Override
    @Transactional
    public Game saveGame(Game newGame, int franchiseId) throws  DataException, EntityNotFoundException{
        try{
            if(!franchiseRepo.existsById(franchiseId)){
                throw new EntityNotFoundException(Franchise.class, franchiseId);
            }
            return gameRepo.save(newGame);
        }catch(PersistenceException pe){
            throw new DataException("Failed to save new game");
        }
    }

    @Override
    @Transactional
    public Game updateGame(Game game, int franchiseId) throws EntityNotFoundException {
        if(!gameRepo.existsById(game.getGameId())){
            throw new EntityNotFoundException(Game.class, game.getGameId());
        }
        Franchise f = franchiseRepo.findById(franchiseId)
                .orElseThrow(()->  new EntityNotFoundException(Franchise.class, franchiseId));
        game.setFranchise(f);
        return gameRepo.save(game);
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