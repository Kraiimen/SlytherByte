package com.slytherin.slytherbyte.models.services.tag;

import com.slytherin.slytherbyte.models.entities.Tag;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.tag.JpaTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaTagService implements TagService {
    private JpaTagRepository tagRepo;

    @Autowired
    public JpaTagService(JpaTagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepo.findAll();
    }

    @Override
    public List<Tag> findTagsByGameId(int id) throws DataException {
        return tagRepo.getTagsByGameId(id);
    }
}
