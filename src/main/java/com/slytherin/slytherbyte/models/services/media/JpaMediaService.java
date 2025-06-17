package com.slytherin.slytherbyte.models.services.media;

import com.slytherin.slytherbyte.models.entities.Media;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.media.JpaMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaMediaService implements MediaService {
    private JpaMediaRepository mediaRepo;

    @Autowired
    public JpaMediaService(JpaMediaRepository mediaRepo) {
        this.mediaRepo = mediaRepo;
    }

    @Override
    public Media findGameTrailers(int id) throws DataException {
        return mediaRepo.getGameTrailers(id);
    }

    @Override
    public List<Media> findMediaByGameId(int id) throws DataException {
        return mediaRepo.getMediaByGameId(id);
    }
}
