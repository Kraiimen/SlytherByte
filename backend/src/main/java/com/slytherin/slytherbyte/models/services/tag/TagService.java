package com.slytherin.slytherbyte.models.services.tag;

import com.slytherin.slytherbyte.models.entities.Tag;
import com.slytherin.slytherbyte.models.exceptions.DataException;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags() throws DataException;
    List<Tag> findTagsByGameId(int id) throws DataException;
}
