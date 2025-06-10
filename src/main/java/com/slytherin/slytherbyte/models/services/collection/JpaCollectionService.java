package com.slytherin.slytherbyte.models.services.collection;

import com.slytherin.slytherbyte.dtos.CollectionDto;
import com.slytherin.slytherbyte.models.repositories.collection.JpaCollectionRepository;
import com.slytherin.slytherbyte.models.repositories.userprofile.JpaUserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaCollectionService implements CollectionService {

    private JpaCollectionRepository collectionRepo;
    private JpaUserProfileRepository userProfileRepo;

    public JpaCollectionService(JpaCollectionRepository collectionRepo, JpaUserProfileRepository userProfileRepo) {
        this.collectionRepo = collectionRepo;
        this.userProfileRepo = userProfileRepo;
    }

    @Override
    public List<CollectionDto> getAllCollections() {
        return List.of();
    }

    @Override
    public CollectionDto getCollectionById(int collectionId) {
        return null;
    }

    @Override
    public CollectionDto createCollection(CollectionDto collectionDto) {
        return null;
    }

    @Override
    public CollectionDto updateCollection(int collectionId, CollectionDto collectionDto) {
        return null;
    }

    @Override
    public boolean deleteCollection(int collectionId) {
        return false;
    }
}
