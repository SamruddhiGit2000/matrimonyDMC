package com.vivah.app.service;


import com.vivah.app.dtos.RegistrationDto;
import com.vivah.app.dtos.UserUpdateRequest;
import com.vivah.app.model.AdminUsers;
import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.User;
import com.vivah.app.repo.AdminUsersRepository;
import com.vivah.app.repo.MatrimonyProfileRepository;
import com.vivah.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminUsersRepository adminUsersRepository;

    @Autowired
    private MatrimonyProfileRepository profileRepository;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).get(0);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public AdminUsers authenticateAdmin(String username, String password) {
        AdminUsers user = adminUsersRepository.findByUsername(username).get(0);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public void registerUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        userRepository.save(user);

        MatrimonyProfile profile = new MatrimonyProfile();
        profile.setFirstName(registrationDto.getFirstName());
        profile.setLastName(registrationDto.getLastName());
        profile.setGender(registrationDto.getGender());
        profile.setDateOfBirth(registrationDto.getDateOfBirth());
        profile.setMaritalStatus(registrationDto.getMaritalStatus());
        profile.setAddress(registrationDto.getAddress());
        profile.setReligion(registrationDto.getReligion());
        profile.setEmail(registrationDto.getEmail());
        profile.setMobileNumber(registrationDto.getMobileNumber());
        profile.setEducation(registrationDto.getEducation());
        profile.setOccupation(registrationDto.getOccupation());
        profile.setIncome(registrationDto.getIncome());
        profile.setAboutMe(registrationDto.getAboutMe());
        profile.setUser(user);

//        if (registrationDto.getProfilePhoto() != null && registrationDto.getProfilePhoto().length > 0) {
//            profile.setProfilePhoto(registrationDto.getProfilePhoto());
//        }

        user.setProfile(profile);
        User userSaved = userRepository.findByUsername(registrationDto.getUsername()).get(0);
        profile.setUserId(userSaved.getId());
        profileRepository.save(profile);

//        profileRepository.save(profile);
    }
    public boolean editUser(UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (request.getUsername() != null && !request.getUsername().isEmpty()) {
                user.setUsername(request.getUsername());
            }
            if (request.getPassword() != null && !request.getPassword().isEmpty()) {
                user.setPassword(request.getPassword());
            }
            // Handle additional fields as necessary

            userRepository.save(user);
            return true;
        }
        return false;
    }
}
