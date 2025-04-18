package com.dhanesh.social.controllers;

import com.dhanesh.social.models.User;
import com.dhanesh.social.models.User;
import com.dhanesh.social.services.UserService;
import com.dhanesh.social.utils.JwtUtil;
import com.dhanesh.social.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordUtil passwordUtil;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordUtil.hashPassword(user.getPassword()));
        userService.createUser(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
         User foundUser = userService.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found")); 

        if (!passwordUtil.matches(user.getPassword(), foundUser.getPassword())) {
            return new ResponseEntity<>("Invalid credentials!", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(foundUser.getId());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}