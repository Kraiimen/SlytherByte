package com.slytherin.slytherbyte.models.services.gamemedia;

import com.slytherin.slytherbyte.models.entities.GameMedia;
import com.slytherin.slytherbyte.models.exceptions.DataException;

import java.util.List;

public interface GameMediaService {
    GameMedia findGameTrailers(int id) throws DataException;
    List<GameMedia> findMediaByGameId(int id) throws DataException;
}
