package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.GameMedia;

public record GameMediaDto(int mediaId, String mediaUrl) {
    public static GameMediaDto toDto(GameMedia gameMedia){
        return new GameMediaDto(gameMedia.getMediaId(), gameMedia.getMediaUrl());
    }
    public GameMedia toEntity(){
        return new GameMedia(this.mediaId, this.mediaUrl);
    }
}