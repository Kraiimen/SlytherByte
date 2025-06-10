package com.slytherin.slytherbyte.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, max = 16, message = "Password must be at least 8 characters long")
    private String password;

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
