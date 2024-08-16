package com.vivah.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vivah.app.assembler.Assembler;
import com.vivah.app.dtos.LoginDto;
import com.vivah.app.dtos.ProfileDto;
import com.vivah.app.dtos.RegistrationDto;
import com.vivah.app.dtos.UserUpdateRequest;
import com.vivah.app.model.AdminUsers;
import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.User;
import com.vivah.app.repo.UserRepository;
import com.vivah.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Assembler assembler;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationDto registrationDto) {
        List<User> users = userRepository.findByUsername(registrationDto.getUsername());
        if (users != null && !users.isEmpty()) { // Check for non-empty list
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already in use"); // Use CONFLICT for clarity
        }
        userService.registerUser(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }
//    @PostMapping("/register")
//    public ResponseEntity<String> register(
//            @ModelAttribute RegistrationDto registrationDto,
//            @RequestParam("profilePhoto") MultipartFile profilePhoto) {
//
//        List<User> users = userRepository.findByUsername(registrationDto.getUsername());
//        if (users != null && !users.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already in use");
//        }
//
//        if (profilePhoto != null && !profilePhoto.isEmpty()) {
//            try {
//                registrationDto.setProfilePhoto(profilePhoto.getBytes());
//            } catch (IOException e) {
//                logger.log(Level.SEVERE, "Error processing profile photo", e);
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile photo");
//            }
//        }
//
//        userService.registerUser(registrationDto);
//        return ResponseEntity.ok("User registered successfully");
//    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            User user = userService.authenticate(loginDto.getUsername(), loginDto.getPassword());
            MatrimonyProfile profile = user.getProfile();

            // Create DTO to avoid serialization issues
            ProfileDto profileDto = assembler.convertToProfileDto(profile);

            return ResponseEntity.ok(profileDto);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<?> Adminogin(@RequestBody LoginDto loginDto) {
        try {
            AdminUsers user = userService.authenticateAdmin(loginDto.getUsername(), loginDto.getPassword());

            return ResponseEntity.ok("Admin User successfully logged in");
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
    @PutMapping("/users/edit")
    public ResponseEntity<?> editUser(@RequestBody UserUpdateRequest request) {
        boolean result = userService.editUser(request);
        if (result) {
            return ResponseEntity.ok().body("User updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user");
        }

    }}

