package com.slytherin.slytherbyte.models.services.authentication;

import com.slytherin.slytherbyte.models.entities.UserAccount;
import com.slytherin.slytherbyte.models.entities.UserProfile;
import com.slytherin.slytherbyte.models.exceptions.AuthenticationException;
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
    public void register(RegisterRequest input) throws AuthenticationException {
        checkEmail(input.getEmail());
        checkPasswordLength(input.getPassword());
        checkPasswords(input.getPassword(), input.getRepeatedPassword());

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

    private void checkEmail(String email) throws AuthenticationException {
        if (userAccountRepo.findByEmail(email).isPresent()) {
            throw new AuthenticationException("Email is already taken");
        }
    }

    private void checkPasswords(String password, String repeatedPassword) throws AuthenticationException {
        if (!password.equals(repeatedPassword)) {
            throw new AuthenticationException("Passwords do not match");
        }
    }

    private void checkPasswordLength(String password) throws AuthenticationException {
        if (password.length() < 8 || password.length() > 16) {
            throw new AuthenticationException("Password must be between 8 and 16 characters");
        }

        if (!password.contains("[A-Z]")) {
            throw new AuthenticationException("Password must contain at least one uppercase letter");
        }

        if (!password.contains("[0-9]")) {
            throw new AuthenticationException("Password must contain at least one digit");
        }

        if (!password.contains("^[a-zA-Z0-9]+$")) {
            throw new AuthenticationException("Password must contain at least one special character");
        }
    }
}
