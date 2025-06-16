package com.slytherin.slytherbyte.models.repositories.publisher;

import com.slytherin.slytherbyte.models.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPublisherRepository extends JpaRepository<Publisher, Integer> {
}
