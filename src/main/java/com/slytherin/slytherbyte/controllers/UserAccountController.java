package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.dtos.UserAccountDto;
import com.slytherin.slytherbyte.models.exceptions.DataException;
import com.slytherin.slytherbyte.models.exceptions.EntityNotFoundException;
import com.slytherin.slytherbyte.models.services.useraccount.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user-accounts")
public class UserAccountController {
    private UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountDto> getUserAccountById(@PathVariable int id) throws DataException, EntityNotFoundException {
        return ResponseEntity.ok(UserAccountDto.toDto(userAccountService.findById(id)));
    }
}
