package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.entities.UserProfile;

public record UserAccountDto(int userAccountId, String usernameAccount, String email, String password,
                             int userProfileId, String authority) {

    public static UserAccountDto toDto(UserAccount userAccount){
        return new UserAccountDto(userAccount.getUserAccountId(), userAccount.getUsernameAccount(),
                userAccount.getUsername(), userAccount.getPassword(),
                userAccount.getUserProfile().getUserProfileId(), userAccount.getAuthority());
    }

    public UserAccount toEntity(){
        return new UserAccount(this.userAccountId, this.usernameAccount, this.email, this.password, null, this.authority);
    }
}
