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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    public AuthenticationResponse login(AuthenticationRequest input) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        UserAccount ua = userAccountRepo.findByEmail(input.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(new HashMap<>(), ua);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    @Transactional
    public void register(RegisterRequest input) throws AuthenticationException {
        checkIfEmailIsTaken(input.getEmail());
        checkIfPasswordIsValid(input.getPassword());
        checkIfPasswordsAreEqual(input.getPassword(), input.getRepeatedPassword());

        UserAccount ua = buildNewUser(input);
        userAccountRepo.save(ua);
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

    private void checkIfEmailIsTaken(String email) throws AuthenticationException {
        if (userAccountRepo.findByEmail(email).isPresent()) {
            throw new AuthenticationException(Map.of("email", Map.of("taken", "Email is already taken")));
        }
    }

    private void checkIfPasswordsAreEqual(String password, String repeatedPassword) throws AuthenticationException {
        if (!password.equals(repeatedPassword)) {
            throw new AuthenticationException(Map.of("repeatedPassword", Map.of("mismatch", "Passwords do not match")));
        }
    }

    private void checkIfPasswordIsValid(String password) throws AuthenticationException {
        Map<String, String> errors = new HashMap<>();
        if (password.length() < 8 || password.length() > 16) {
            errors.put("length", "Password must be between 8 and 16 characters");
        }

        if (!Pattern.matches(".*[A-Z].*", password)) {
            errors.put("uppercase", "Password must contain at least one uppercase letter");
        }

        if (!Pattern.matches(".*[0-9].*", password)) {
            errors.put("digit", "Password must contain at least one digit");
        }

        if (!Pattern.matches(".*[^a-zA-Z0-9].*", password)) {
            errors.put("specialCharacter", "Password must contain at least one special character");
        }

        if (!errors.isEmpty()) {
            throw new AuthenticationException(Map.of("password", errors));
        }
    }
}
