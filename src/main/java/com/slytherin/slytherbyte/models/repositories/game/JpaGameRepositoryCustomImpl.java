package com.slytherin.slytherbyte.models.repositories.game;

import com.slytherin.slytherbyte.models.entities.*;
import com.slytherin.slytherbyte.models.repositories.searchcriteria.GameFilterCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class JpaGameRepositoryCustomImpl implements JpaGameRepositoryCustom {
    EntityManager entityManager;

    @Autowired
    JpaGameRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Game> searchGames(GameFilterCriteria filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = criteriaBuilder.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);

        List<Predicate> queryFilters = new ArrayList<>();
        boolean groupByGameId = false;

        if (filters.getTitle() != null) {
            Expression<String> titleToLower = criteriaBuilder.lower(root.get("title"));
            String pattern = "%" + filters.getTitle().toLowerCase() + "%";
            queryFilters.add(criteriaBuilder.like(titleToLower, pattern));
        }

        if (filters.getReleaseDate() != null) {
            queryFilters.add(criteriaBuilder.greaterThanOrEqualTo(root.get("releaseDate"), filters.getReleaseDate()));
        }

        if (filters.getPlatforms() != null && !filters.getPlatforms().isEmpty()) {
            Join<Game, Platform> join = root.join("platforms");
            queryFilters.add(join.get("name").in(filters.getPlatforms()));
            query.having(
                    criteriaBuilder.equal(
                            criteriaBuilder.countDistinct(join.get("name")),
                            filters.getPlatforms().size()
                    )
            );
            groupByGameId = true;
        }
        if (filters.getLanguages() != null && !filters.getLanguages().isEmpty()) {
            Join<Game, Language> join = root.join("languages");
            queryFilters.add(join.get("name").in(filters.getLanguages()));
            query.having(
                    criteriaBuilder.equal(
                            criteriaBuilder.countDistinct(join.get("name")),
                            filters.getLanguages().size()
                    )
            );
            groupByGameId = true;
        }
        if (filters.getTags() != null && !filters.getTags().isEmpty()) {
            Join<Game, Tag> join = root.join("tags");
            queryFilters.add(join.get("name").in(filters.getTags()));
            query.having(
                    criteriaBuilder.equal(
                            criteriaBuilder.countDistinct(join.get("name")),
                            filters.getTags().size()
                    )
            );
            groupByGameId = true;
        }
        if (filters.getStores() != null && !filters.getStores().isEmpty()) {
            Join<Game, Store> join = root.join("stores");
            queryFilters.add(join.get("name").in(filters.getStores()));
            query.having(
                    criteriaBuilder.equal(
                            criteriaBuilder.countDistinct(join.get("name")),
                            filters.getStores().size()
                    )
            );
            groupByGameId = true;
        }
        if (filters.getPublishers() != null && !filters.getPublishers().isEmpty()) {
            Join<Game, Publisher> join = root.join("publishers");
            queryFilters.add(join.get("name").in(filters.getPublishers()));
            query.having(
                    criteriaBuilder.equal(
                            criteriaBuilder.countDistinct(join.get("name")),
                            filters.getPublishers().size()
                    )
            );
            groupByGameId = true;
        }

        if(groupByGameId) {
            query.groupBy(root.get("gameId"));
        }

        query.select(root).where(criteriaBuilder.and(queryFilters.toArray(new Predicate[0])));


            if (filters.isSortedByName()) {
                query.orderBy(criteriaBuilder.asc(root.get("title")));
            }
            if (filters.isSortedByDate()) {
                query.orderBy(criteriaBuilder.asc(root.get("releaseDate")));
            }

        return entityManager.createQuery(query).getResultList();
    }
}
