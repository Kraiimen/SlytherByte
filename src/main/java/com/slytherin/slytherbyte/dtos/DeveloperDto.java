package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Developer;

public record DeveloperDto(int developerId, String name) {

    public static DeveloperDto toDto(Developer d){
        return new DeveloperDto(d.getDeveloperId(), d.getName());
    }

    public Developer toEntity(DeveloperDto dto){
        return new Developer(this.developerId, this.name);
    }
}
