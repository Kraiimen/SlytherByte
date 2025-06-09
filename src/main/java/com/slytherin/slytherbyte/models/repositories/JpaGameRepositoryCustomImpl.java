package com.slytherin.slytherbyte.models.repositories;

import com.slytherin.slytherbyte.models.entities.Game;
import com.slytherin.slytherbyte.models.searchcriteria.GameFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
            queryFilters.add(criteriaBuilder.equal(root.get("game").get("title"), filters.getTitle()));
        }
        if(filters.getReleaseDate() != null){
            queryFilters.add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get("game").get("releaseDate"), filters.getReleaseDate()));
        }
        if(!filters.getPlatforms().isEmpty()){
            for(String platform : filters.getPlatforms()){
                queryFilters.add(criteriaBuilder.isMember(platform, root.get("game").get("platforms")));
            }
        }
        if(!filters.getLanguages().isEmpty()){
            for(String language : filters.getLanguages()){
                queryFilters.add(criteriaBuilder.isMember(language, root.get("game").get("languages")));
            }
        }
        if(!filters.getTags().isEmpty()){
            for(String tag : filters.getTags()){
                queryFilters.add(criteriaBuilder.isMember(tag, root.get("game").get("tags")));
            }
        }
        if(!filters.getStores().isEmpty()){
            for(String store : filters.getStores()){
                queryFilters.add(criteriaBuilder.isMember(store, root.get("game").get(store)));
            }
        }
        if(!filters.getPublishers().isEmpty()){
            for(String publisher : filters.getLanguages()){
                queryFilters.add(criteriaBuilder.isMember(publisher, root.get("game").get("publishers")));
            }
        }
        query.select(root).where(criteriaBuilder.and(queryFilters.toArray(new Predicate[0])));

        if(!filters.isNameSorter()){
            query.orderBy(criteriaBuilder.asc(root.get("name")));
        }else{
            query.orderBy(criteriaBuilder.desc(root.get("name")));
        }
        if(!filters.isDateSorter()){
            query.orderBy(criteriaBuilder.asc(root.get("releaseDate")));
        }else{
            query.orderBy(criteriaBuilder.desc(root.get("releaseDate")));
        }

        return entityManager.createQuery(query).getResultList();
    }
}
