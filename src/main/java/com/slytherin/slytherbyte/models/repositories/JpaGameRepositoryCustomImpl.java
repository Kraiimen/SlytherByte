package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.entities.Tag;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JpaGameRepositoryCustomImpl implements JpaGameRepositoryCustom {
    EntityManager entityManager;
    @Autowired
    JpaGameRepositoryCustomImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Game> searchGames(GameFilterCriteria filters){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = criteriaBuilder.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);
        List<Predicate> queryFilters = new ArrayList<>();

        if(filters.getTitle() != null){
            queryFilters.add(criteriaBuilder.like(root.get("title"), filters.getTitle()));
        }
        if(filters.getReleaseDate() != null){
            queryFilters.add(criteriaBuilder.greaterThanOrEqualTo(root.get("releaseDate"), filters.getReleaseDate()));
        }
        if(filters.getPlatforms() != null && !filters.getPlatforms().isEmpty()){
            for(String platform : filters.getPlatforms()){
                queryFilters.add(criteriaBuilder.isMember(platform, root.get("platforms")));
            }
        }
        if(filters.getLanguages() != null && !filters.getLanguages().isEmpty()){
            for(String language : filters.getLanguages()){
                queryFilters.add(criteriaBuilder.isMember(language, root.get("languages")));
            }
        }
        if(filters.getTags() != null && !filters.getTags().isEmpty()){
            Join<Game, Tag> join = root.join("tags");
            query.groupBy(root.get("gameId"));
            query.having(
                    criteriaBuilder.equal(criteriaBuilder.countDistinct(join.get("name")), filters.getTags().size()),
                            criteriaBuilder.and(join.get("name").in(filters.getTags()))
            );

        }
        if(filters.getStores() != null && !filters.getStores().isEmpty()){
            for(String store : filters.getStores()){
                queryFilters.add(criteriaBuilder.isMember(store, root.get("stores")));
            }
        }
        if(filters.getPublishers() != null && !filters.getPublishers().isEmpty()){
            for(String publisher : filters.getLanguages()){
                queryFilters.add(criteriaBuilder.isMember(publisher, root.get("publishers")));
            }
        }
        query.select(root).where(criteriaBuilder.and(queryFilters.toArray(new Predicate[0])));

        if (filters.isNameSorter() != null) {
            if(!filters.isNameSorter()){
                query.orderBy(criteriaBuilder.asc(root.get("title")));
            }else{
                query.orderBy(criteriaBuilder.desc(root.get("title")));
            }
        }
        if (filters.isDateSorter() != null) {
            if (!filters.isDateSorter()) {
                query.orderBy(criteriaBuilder.asc(root.get("releaseDate")));
            } else {
                query.orderBy(criteriaBuilder.desc(root.get("releaseDate")));
            }
        }

        return entityManager.createQuery(query).getResultList();
    }
}
