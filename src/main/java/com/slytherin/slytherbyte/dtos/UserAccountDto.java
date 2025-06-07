package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.entities.UserProfile;

public record UserAccountDto(int userAccountId, String username, String email, String password, int userProfileId) {

    public static UserAccountDto toDto(UserAccount userAccount){
        return new UserAccountDto(userAccount.getUserAccountId(), userAccount.getUsername(), userAccount.getEmail(),
                userAccount.getPassword(), userAccount.getUserProfile().getUserProfileId());
    }

    public UserAccount toEntity(){
        return new UserAccount(this.userAccountId, this.username, this.email, this.password, null);
    }
}
