package com.slytherin.slytherbyte.models.services.media;

import com.slytherin.slytherbyte.models.entities.Media;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.media.JpaMediaRepository;

import java.util.List;

public interface MediaService {
    Media findGameTrailers(int id) throws DataException;
    List<Media> findMediaByGameId(int id) throws DataException;
}
