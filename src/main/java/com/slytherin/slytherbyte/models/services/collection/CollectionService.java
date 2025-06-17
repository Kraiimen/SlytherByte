package com.slytherin.slytherbyte.models.services.collection;

import com.slytherin.slytherbyte.dtos.CollectionDto;

import java.util.List;

public interface CollectionService {

    List<CollectionDto> getAllCollections();
    CollectionDto getCollectionById(int collectionId);
    CollectionDto createCollection(CollectionDto collectionDto);
    CollectionDto updateCollection(int collectionId, CollectionDto collectionDto);
    boolean deleteCollection(int collectionId);
}
