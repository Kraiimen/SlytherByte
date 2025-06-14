package com.slytherin.slytherbyte.models.repositories.tag;

import com.slytherin.slytherbyte.models.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTagRepository extends JpaRepository<Tag, Integer> {

}
