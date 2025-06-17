package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Media;

public record MediaDto(int mediaId, String mediaUrl) {
    public static MediaDto toDto(Media media){
        return new MediaDto(media.getMediaId(), media.getMediaUrl());
    }
    public Media toEntity(){
        return new Media(this.mediaId, this.mediaUrl);}
}