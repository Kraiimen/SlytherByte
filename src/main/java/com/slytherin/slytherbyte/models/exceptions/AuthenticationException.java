package com.slytherin.slytherbyte.models.exceptions;

import java.util.Map;

public class AuthenticationException extends Exception {
    private Map<String, Map<String, String>> errors;

    public AuthenticationException(Map<String, Map<String, String>> errors) {
        super();
        this.errors = errors;
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public Map<String, Map<String, String>> getErrors() {
        return errors;
    }
}
