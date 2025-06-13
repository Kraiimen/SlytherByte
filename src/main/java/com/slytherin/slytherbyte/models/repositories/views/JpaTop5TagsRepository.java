package com.slytherin.slytherbyte.models.repositories.views;

import com.slytherin.slytherbyte.models.entities.views.stats.Top5Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTop5TagsRepository extends JpaRepository <Top5Tags, Integer> {
}
