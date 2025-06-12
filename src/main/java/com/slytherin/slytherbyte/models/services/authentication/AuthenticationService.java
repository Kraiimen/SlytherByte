package com.slytherin.slytherbyte.models.services.authentication;

import com.slytherin.slytherbyte.models.exceptions.AuthenticationException;
import com.slytherin.slytherbyte.models.request.AuthenticationRequest;
import com.slytherin.slytherbyte.models.request.RegisterRequest;
import com.slytherin.slytherbyte.models.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws AuthenticationException;
    AuthenticationResponse login(AuthenticationRequest input);
}
