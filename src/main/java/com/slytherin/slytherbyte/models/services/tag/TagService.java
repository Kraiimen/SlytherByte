package com.slytherin.slytherbyte.models.services.tag;

import com.slytherin.slytherbyte.models.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();
}
