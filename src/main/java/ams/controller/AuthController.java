package ams.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import ams.config.AuthRequest;
import ams.config.JwtService;
import ams.model.Farmer;
import ams.model.User;
import ams.repository.FarmerRepository;
import ams.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid login credentials!");
        }

        // Try to find user
        User user = userRepository.findByEmail(authRequest.getEmail());
        Farmer farmer = farmerRepository.findByEmail(authRequest.getEmail()).orElse(null);

        String role;
        if (user != null) {
            role = "ROLE_" + user.getRole().name();
        } else if (farmer != null) {
            role = "ROLE_" + farmer.getRole().name();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + authRequest.getEmail());
        }

        String token = jwtService.generateToken(authRequest.getEmail(), Collections.singletonList(role));

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", role);

        return ResponseEntity.ok(response);
    }
}
