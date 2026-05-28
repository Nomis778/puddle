package com.github.nomis778.puddle.server.auth;

import com.github.nomis778.puddle.server.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAndGetToken(@RequestBody User user) {
        try {
            authService.registerUser(user);
            String jwt = authService.login(user);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Error: Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAndGetToken(@RequestBody User user) {
        try {
            String jwt = authService.login(user);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Error: Login failed " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
