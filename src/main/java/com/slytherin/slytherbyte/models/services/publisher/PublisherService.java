package com.slytherin.slytherbyte.models.services.publisher;

import com.slytherin.slytherbyte.models.entities.Publisher;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAllPublishers() throws DataException;
    List<Publisher> findPublishersByGameId(int id) throws DataException;
}
