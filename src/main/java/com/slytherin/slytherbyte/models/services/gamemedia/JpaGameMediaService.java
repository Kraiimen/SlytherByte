package com.slytherin.slytherbyte.models.services.gamemedia;

import com.slytherin.slytherbyte.models.entities.GameMedia;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.gamemedia.JpaGameMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaGameMediaService implements GameMediaService {
    private JpaGameMediaRepository mediaRepo;

    @Autowired
    public JpaGameMediaService(JpaGameMediaRepository mediaRepo) {
        this.mediaRepo = mediaRepo;
    }

    @Override
    public GameMedia findGameTrailers(int id) throws DataException {
        return mediaRepo.getGameTrailers(id);
    }

    @Override
    public List<GameMedia> findMediaByGameId(int id) throws DataException {
        return mediaRepo.getMediaByGameId(id);
    }
}
