package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Tag;

public record TagDto(int tagId, String name) {

    public static TagDto toDto(Tag tag){
        return new TagDto(tag.getTagId(), tag.getName());
    }

    public Tag toEntity(){
        return new Tag(this.tagId, this.name);
    }
}
