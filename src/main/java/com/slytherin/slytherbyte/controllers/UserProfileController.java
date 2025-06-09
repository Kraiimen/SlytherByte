package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.UserProfileDto;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user-profiles")
public class UserProfileController {
    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getAll() {
        try {
            List<UserProfile> userProfiles = userProfileService.findAllUserProfiles();
            return ResponseEntity.ok(userProfiles.stream().map(UserProfileDto::toDto).toList());
        } catch (DataException e) {
            throw new RuntimeException(e);
        }

        // TODO: create query params to find user by name like
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getById(@PathVariable("id") int userProfileId) throws DataException, EntityNotFoundException {
        UserProfileDto upDto = UserProfileDto.toDto(userProfileService.findUserProfileById(userProfileId));
        return ResponseEntity.ok(upDto);
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDto) throws EntityNotFoundException, DataException {
        UserProfile userProfile = userProfileDto.toEntity();
        UserProfileDto upDto=UserProfileDto.toDto(userProfileService.createUserProfile(userProfile, userProfileDto.userAccountId()));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(upDto.userProfileId())
                .toUri();
        return ResponseEntity.created(location).body(upDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDto> updateById(@PathVariable("id") int userProfileId, @RequestBody UserProfileDto userProfileDto)
            throws DataException, EntityNotFoundException {
        if (userProfileDto.userProfileId() != userProfileId) {
            return ResponseEntity.badRequest().build();
        }
        UserProfileDto upDto = UserProfileDto
                .toDto(userProfileService.updateUserProfile(userProfileDto.toEntity(), userProfileDto.userAccountId()));
        return ResponseEntity.ok(upDto);
    }
}
