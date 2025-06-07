package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.UserProfileDto;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {
    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService=userProfileService;
    }

    @GetMapping
    public ResponseEntity<List<UserProfile>> getAll(){
        List<UserProfile> userProfiles=userProfileService.findAllUserProfiles();

        return ResponseEntity.ok(userProfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable("id") int userProfileId){
        Optional<UserProfile> userProfile=userProfileService.findUserProfileById(userProfileId);
        if(userProfile.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userProfile.get());
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDto){
        UserProfile userProfile=userProfileDto.toEntity();
        userProfileService.createUserProfile(userProfile);
        var savedUserProfile=UserProfileDto.toDto(userProfile); //need to add smth????
        return ResponseEntity.ok(savedUserProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById (@PathVariable("id") int userProfileId, @RequestBody UserProfileDto userProfileDto){
        if(userProfileDto.userProfileId()!=userProfileId){
            return ResponseEntity.badRequest().build();
        }

        Optional<UserProfile> userProfile=userProfileService.findUserProfileById(userProfileId);
        if(userProfile.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        userProfileService.updateUserProfile(userProfileDto.toEntity());
        return ResponseEntity.ok(UserProfileDto.toDto(userProfile.get()));
    }
}
