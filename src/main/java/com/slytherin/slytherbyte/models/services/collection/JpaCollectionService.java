package com.slytherin.slytherbyte.models.services.collection;

import com.slytherin.slytherbyte.dtos.CollectionDto;
import com.slytherin.slytherbyte.models.entities.Collection;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.repositories.collection.JpaCollectionRepository;
import com.slytherin.slytherbyte.models.repositories.userprofile.JpaUserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return collectionRepo.findAll()
                .stream()
                .map(CollectionDto::toDto)
                .toList();
    }

    @Override
    public CollectionDto getCollectionById(int collectionId) {
        return collectionRepo.findById(collectionId)
                .map(CollectionDto::toDto)
                .orElse(null);
    }

    @Override
    public CollectionDto createCollection(CollectionDto collectionDto) {
        UserProfile userProfile = userProfileRepo.findById(collectionDto.userProfileId()).orElse(null);
        if (userProfile == null) {
            return null;
        }

        Collection entity = new Collection(
                0,
                collectionDto.name(),
                collectionDto.description(),
                userProfile
        );

        Collection saved = collectionRepo.save(entity);
        return CollectionDto.toDto(saved);
    }

    @Override
    public CollectionDto updateCollection(int collectionId, CollectionDto collectionDto) {
        Optional<Collection> optional = collectionRepo.findById(collectionId);
        if (optional.isEmpty()) {
            return null;
        }

        Collection existing = optional.get();
        existing.setName(collectionDto.name());
        existing.setDescription(collectionDto.description());

        Collection saved = collectionRepo.save(existing);
        return CollectionDto.toDto(saved);
    }

    @Override
    public boolean deleteCollection(int collectionId) {
        if (!collectionRepo.existsById(collectionId)) {
            return false;
        }

        collectionRepo.deleteById(collectionId);
        return true;
    }
}
