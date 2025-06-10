package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Publisher;

public record PublisherDto (int publisherId, String name){

    public static PublisherDto toDto(Publisher publisher){
        return new PublisherDto(publisher.getPublisherId(), publisher.getName());
    }

    public Publisher toEntity(){
        return new Publisher(this.publisherId, this.name);
    }
}

