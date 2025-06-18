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
    public AuthenticationResponse login(AuthenticationRequest input) throws AuthenticationException {
        UserAccount ua = checkIfAccountIsValid(input.getEmail(), input.getPassword());

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        String jwtToken = jwtService.generateToken(new HashMap<>(), ua, input.isRememberMeSelected());
        return new AuthenticationResponse(ua.getUserAccountId(), jwtToken);
    }

    @Override
    @Transactional
    public void register(RegisterRequest input) throws AuthenticationException {
        checkIfEmailIsTaken(input.getEmail());
        checkIfUsernameIsValid(input.getUsername());
        checkIfPasswordIsValid(input.getPassword());
        checkIfPasswordsAreEqual(input.getPassword(), input.getRepeatedPassword());

        UserAccount ua = buildNewUser(input);
        userAccountRepo.save(ua);
    }

    @Transactional
    private UserAccount buildNewUser(RegisterRequest input) {
        UserProfile up = new UserProfile(0, input.getUsername(), null, "https://static.vecteezy.com/system/resources/previews/004/511/281/original/default-avatar-photo-placeholder-profile-picture-vector.jpg");
        userProfileRepo.save(up);

        return new UserAccount(
                0,
                input.getUsername(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword()),
                up,
                "ROLE_USER"
        );
    }

    private UserAccount checkIfAccountIsValid(String email, String password) throws AuthenticationException{
        UserAccount ua = checkIfEmailExists(email);
        if (!passwordEncoder.matches(password, ua.getPassword())) {
            throw new AuthenticationException(Map.of("login", Map.of("invalidCredentials", "Incorrect email or password")));
        }

        return ua;
    }

    private void checkIfEmailIsTaken(String email) throws AuthenticationException {
        if (userAccountRepo.findByEmail(email).isPresent()) {
            throw new AuthenticationException(Map.of("email", Map.of("taken", "Email is already taken")));
        }
    }

    private UserAccount checkIfEmailExists(String email) throws AuthenticationException {
        return userAccountRepo.findByEmail(email)
                .orElseThrow(
                        () -> new AuthenticationException(
                                Map.of("email", Map.of("notExists", "Email does not exist"))
                        )
                );
    }

    private void checkIfUsernameIsValid(String username) throws AuthenticationException {
        Map<String, String> errors = new HashMap<>();

        if (username.isEmpty() || username.length() > 16) {
            errors.put("length", "Username must be between 1 and 16 characters");
        }

        if (userAccountRepo.findByUsernameAccount(username).isPresent()) {
            errors.put("taken", "Username is already taken");
        }

        if (!errors.isEmpty()) {
            throw new AuthenticationException(Map.of("username", errors));
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
            errors.put("specialChar", "Password must contain at least one special character");
        }

        if (!errors.isEmpty()) {
            throw new AuthenticationException(Map.of("password", errors));
        }
    }

    private void checkIfPasswordsAreEqual(String password, String repeatedPassword) throws AuthenticationException {
        if (!password.equals(repeatedPassword)) {
            throw new AuthenticationException(Map.of("repeatedPassword", Map.of("mismatch", "Passwords do not match")));
        }
    }
}
