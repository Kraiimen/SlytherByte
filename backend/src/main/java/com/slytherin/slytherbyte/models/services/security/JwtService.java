package com.slytherin.slytherbyte.models.services.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateToken(Map<String, Object> claims, UserDetails userDetails, boolean isRememberMeSelected);
}
