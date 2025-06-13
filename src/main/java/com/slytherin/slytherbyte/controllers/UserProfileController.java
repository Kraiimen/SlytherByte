package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.UserProfileDto;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.services.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<UserProfileDto>> getAll(@RequestParam(required = false) String nameLike) throws EntityNotFoundException {
        try {
            if( nameLike==null || nameLike.isEmpty()){
                List<UserProfile> userProfiles = userProfileService.findAllUserProfiles();
                return ResponseEntity.ok(userProfiles.stream().map(UserProfileDto::toDto).toList());
            }
            char first=nameLike.charAt(0);
            if(first=='@'){
                nameLike=nameLike.replace("@", "");
                List<UserProfile> userProfiles=userProfileService.findAllUserProfilesByUsername(nameLike);
                if(userProfiles.isEmpty()){
                    return ResponseEntity.noContent().build();
                }
                List<UserProfileDto> upDto=userProfiles.stream().map(UserProfileDto::toDto).toList();
                return ResponseEntity.ok(upDto);
            }
            List<UserProfile> userProfiles=userProfileService.findAllUserProfilesByName(nameLike);
            List<UserProfileDto> upDto=userProfiles.stream().map(UserProfileDto::toDto).toList();
            return ResponseEntity.ok(upDto);
        } catch (DataException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getById(@PathVariable("id") int userProfileId) throws DataException, EntityNotFoundException {
        UserProfileDto upDto = UserProfileDto.toDto(userProfileService.findUserProfileById(userProfileId));
        return ResponseEntity.ok(upDto);
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDto) throws EntityNotFoundException, DataException {
        UserProfile userProfile = userProfileDto.toEntity();
        UserProfileDto upDto = UserProfileDto.toDto(userProfileService.createUserProfile(userProfile));

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

        UserProfileDto upDto = UserProfileDto.toDto(userProfileService.updateUserProfile(userProfileDto.toEntity()));
        return ResponseEntity.ok(upDto);
    }


}
