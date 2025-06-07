package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Collection;
import com.slytherin.slytherbyte.models.entities.UserProfile;

public record CollectionDto(int collectionId, String name, String description, int userProfileId) {

  public static CollectionDto toDto(Collection collection){
      return new CollectionDto(collection.getCollectionId(), collection.getName(), collection.getDescription(),
              collection.getUserProfile().getUserProfileId());
  }

  public Collection toEntity(){
      return new Collection(this.collectionId, this.name, this.description, null);
  }

}


