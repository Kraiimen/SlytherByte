package com.slytherin.slytherbyte.models.services.publisher;

import com.slytherin.slytherbyte.models.entities.Publisher;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.repositories.publisher.JpaPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaPublisherService implements PublisherService{
    JpaPublisherRepository publisherRepo;

    @Autowired
    public JpaPublisherService(JpaPublisherRepository publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<Publisher> findAllPublishers() throws DataException{
        return publisherRepo.findAll();
    }

    @Override
    public List<Publisher> findPublishersByGameId(int id) throws DataException {
        return publisherRepo.getPublishersByGameId(id);
    }
}
