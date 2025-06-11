package com.slytherin.slytherbyte.models.services.authentication;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.repositories.useraccount.JpaUserAccountRepository;
import com.slytherin.slytherbyte.models.repositories.userprofile.JpaUserProfileRepository;
import com.slytherin.slytherbyte.models.request.AuthenticationRequest;
import com.slytherin.slytherbyte.models.request.RegisterRequest;
import com.slytherin.slytherbyte.models.response.AuthenticationResponse;
import com.slytherin.slytherbyte.models.services.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private JpaUserAccountRepository userAccountRepo;
    private JpaUserProfileRepository userProfileRepo;
    private AuthenticationManager authManager;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(JpaUserAccountRepository userAccountRepo, JpaUserProfileRepository userProfileRepo,
                                     AuthenticationManager authManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userAccountRepo = userAccountRepo;
        this.userProfileRepo = userProfileRepo;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    private UserAccount buildNewUser(RegisterRequest input) {
        String username = input.getEmail().split("@")[0];

        UserProfile up = new UserProfile(0, username, null, null);
        userProfileRepo.save(up);

        return new UserAccount(
                0,
                username,
                input.getEmail(),
                passwordEncoder.encode(input.getPassword()),
                up,
                "ROLE_USER"
        );
    }
}
