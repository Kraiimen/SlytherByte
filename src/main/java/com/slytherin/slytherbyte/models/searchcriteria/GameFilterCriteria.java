package com.slytherin.slytherbyte.models.searchcriteria;

import java.time.LocalDate;
import java.util.List;

public class GameFilterCriteria {
    private String title;
    private LocalDate realeaseDate;
    private List<String> platforms;
    private List<String> languages;
    private List<String> tags;
    private List<String> stores;
    private List<String> publishers;
    private Boolean sortByName;
    private Boolean sortByDate;

    public GameFilterCriteria(String title, LocalDate realeaseDate,
                              List<String> platforms, List<String> languages,
                              List<String> tags, List<String> stores,
                              List<String> publishers, Boolean sortByName,
                              Boolean sortByDate) {
        this.title = title;
        this.realeaseDate = realeaseDate;
        this.platforms = platforms;
        this.languages = languages;
        this.tags = tags;
        this.stores = stores;
        this.publishers = publishers;
        this.sortByName = sortByName;
        this.sortByDate = sortByDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return realeaseDate;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getStores() {
        return stores;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public Boolean isSortedByName() {
        return sortByName;
    }

    public Boolean isSortedByDate() {
        return sortByDate;
    }
}
