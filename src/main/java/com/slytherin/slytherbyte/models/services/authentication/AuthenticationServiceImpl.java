package com.slytherin.slytherbyte.models.services.authentication;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.repositories.useraccount.UserAccountRepository;
import com.slytherin.slytherbyte.models.request.AuthenticationRequest;
import com.slytherin.slytherbyte.models.request.RegisterRequest;
import com.slytherin.slytherbyte.models.response.AuthenticationResponse;
import com.slytherin.slytherbyte.models.services.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserAccountRepository userAccountRepo;
    private AuthenticationManager authManager;
    private JwtService jwtService;

    public AuthenticationServiceImpl(UserAccountRepository userAccountRepo, AuthenticationManager authManager, JwtService jwtService) {
        this.userAccountRepo = userAccountRepo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public void register(RegisterRequest input) throws Exception {
        if (isEmailTaken(input.getEmail())) {
            throw new Exception("Email is already taken");
        }
        UserAccount ua = buildNewUser(input);
        userAccountRepo.save(ua);
    }

    @Override
    @Transactional
    public AuthenticationResponse login(AuthenticationRequest input) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        UserAccount ua = userAccountRepo.findByEmail(input.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(new HashMap<>(), ua);
        return new AuthenticationResponse(jwtToken);
    }

    private boolean isEmailTaken(String email) {
        return userAccountRepo.findByEmail(email).isPresent();
    }

    private UserAccount buildNewUser(RegisterRequest input) {
        String username = input.getEmail().split("@")[0];
        UserProfile up = new UserProfile(0, username, null, null);
        return new UserAccount(0, username, input.getEmail(), input.getPassword(), up, "ROLE_USER");
    }
}
