package com.slytherin.slytherbyte.models.exceptions;

import org.springframework.lang.Nullable;

import java.util.Optional;

public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}
