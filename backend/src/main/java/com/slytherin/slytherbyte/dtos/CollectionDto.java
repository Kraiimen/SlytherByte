package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Collection;
import com.slytherin.slytherbyte.models.entities.UserProfile;

import java.time.LocalDate;

public record CollectionDto(int collectionId, String name, String description, LocalDate createdAt, int userProfileId) {

  public static CollectionDto toDto(Collection collection){
      return new CollectionDto(collection.getCollectionId(), collection.getName(), collection.getDescription(),
              collection.getCreatedAt(), collection.getUserProfile().getUserProfileId());
  }

  public Collection toEntity(){
      return new Collection(collectionId, name, description, createdAt, null);
  }
}


