package com.github.nomis778.puddle.server.auth;

import com.github.nomis778.puddle.server.user.UserRepository;
import com.github.nomis778.puddle.server.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    UserRepository userRepository;

    @Autowired
    public AuthService(AuthenticationManager authManager,
                       PasswordEncoder encoder,
                       JwtUtil jwtUtil,
                       UserRepository userRepository) {

        this.authManager = authManager;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if(userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("Error: Username is already taken!");

        User u = new User(
                user.getUsername(),
                encoder.encode(user.getPassword())
        );
        userRepository.save(u);
    }

    public String login(@RequestBody User user) {
        User toMatch = userRepository.findByUsername(user.getUsername());
        Long idToMatch = toMatch.getId();

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        idToMatch,
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String strId = userDetails.getUsername();
        return jwtUtil.generateToken(Long.parseLong(strId));
    }
}
