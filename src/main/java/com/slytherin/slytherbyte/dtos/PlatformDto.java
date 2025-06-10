package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Platform;

public record PlatformDto(int platformId, String name) {

    public static PlatformDto toDto(Platform platform){
        return new PlatformDto(platform.getPlatformId(), platform.getName());
    }

    public Platform toEntity(){
        return new Platform(this.platformId, this.name);
    }
}
