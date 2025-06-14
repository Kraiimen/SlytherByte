package com.slytherin.slytherbyte.models.services.publisher;

import com.slytherin.slytherbyte.models.entities.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAllPublishers();
}
