package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game_media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_media_id")
    private int mediaId;

    @Column(name = "game_media_url")
    private String mediaUrl;

    @Column(name = "is_trailer")
    private boolean isTrailer;

    @ManyToOne
    @JoinColumn(name = " game_id")
    private Game game;

    public Media(int mediaId, String mediaUrl) {
        this.mediaId = mediaId;
        this.mediaUrl = mediaUrl;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrls(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isTrailer() {
        return isTrailer;
    }

    public void setTrailer(boolean trailer) {
        isTrailer = trailer;
    }
}
