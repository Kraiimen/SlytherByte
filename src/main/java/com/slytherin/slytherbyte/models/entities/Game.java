package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private String summary;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name ="game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platform> platforms = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_stores",
            joinColumns = @JoinColumn(name ="game_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    private List<Store> stores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_developers",
            joinColumns = @JoinColumn(name ="game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> developers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_publishers",
            joinColumns = @JoinColumn(name ="game_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private List<Publisher> publishers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_tags",
            joinColumns = @JoinColumn(name ="game_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_languages",
            joinColumns = @JoinColumn(name ="game_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> languages = new ArrayList<>();

    public Game() {
    }

    public Game(int gameId, String coverImageUrl, String title, LocalDate releaseDate, String summary, Franchise franchise) {
        this.gameId = gameId;
        this.coverImageUrl = coverImageUrl;
        this.title = title;
        this.releaseDate = releaseDate;
        this.summary = summary;
        this.franchise = franchise;
    }

    public int getGameId() {
        return gameId;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getSummary() {
        return summary;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
