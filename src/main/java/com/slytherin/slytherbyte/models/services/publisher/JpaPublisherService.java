package com.slytherin.slytherbyte.models.services.publisher;

import com.slytherin.slytherbyte.models.entities.Publisher;
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
    public List<Publisher> findAllPublishers() {
        return publisherRepo.findAll();
    }
}
