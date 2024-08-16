package com.vivah.app.controller;

import com.vivah.app.assembler.Assembler;
import com.vivah.app.dtos.InterestUpdateRequest;
import com.vivah.app.dtos.ProfileDto;
import com.vivah.app.dtos.UserInterestsDTO;
import com.vivah.app.dtos.UserProfilesResponse;
import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.UserInterests;
import com.vivah.app.repo.UserInterestRepository;
import com.vivah.app.service.MatrimonyProfileService;
import com.vivah.app.service.UserInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserInterestController {

    @Autowired
    private UserInterestRepository userInterestRepository;

    @Autowired
    private UserInterestService userInterestService;

    @Autowired
    private MatrimonyProfileService matrimonyProfileService;

    @Autowired
    private Assembler assembler;

    // Add interest
    @PostMapping("/addInterest")
    public ResponseEntity<String> addInterest(@RequestBody UserInterestsDTO interest) {
        UserInterests userInterest = assembler.convertToUserInterestsEntity(interest);
        userInterestRepository.save(userInterest);
        return ResponseEntity.ok("Interest added successfully.");
    }

    // Remove interest
    @PostMapping("/removeInterest")
    public ResponseEntity<String> removeInterest(@RequestBody UserInterestsDTO interest) {
        try {
            userInterestService.removeInterest(interest.getUserId(), interest.getInterestedInUserId());
            return ResponseEntity.ok("Interest removed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Interest record not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove interest.");
        }
    }

    // Endpoint to get user interests
    @GetMapping("/userInterests/{userId}")
    public ResponseEntity<List<UserInterests>> getUserInterests(@PathVariable Long userId) {
        List<UserInterests> interests = userInterestService.getInterestsByUserId(userId);
        return ResponseEntity.ok(interests);
    }

    // Combined endpoint to get profiles and user interests
    @GetMapping("/profilesWithInterests/{userId}")
    public ResponseEntity<UserProfilesResponse> getProfilesWithInterests(@PathVariable Long userId) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getAllProfilesExceptUser(userId);
        List<UserInterests> interests = userInterestService.getInterestsByUserId(userId);

        List<UserInterestsDTO> userInterestsDtos = new ArrayList<UserInterestsDTO>();
        List<ProfileDto> profilesDto = new ArrayList<ProfileDto>();

        for(UserInterests userInterest: interests){
            UserInterestsDTO userInterestsDTO = assembler.convertToUserInterestsDTO(userInterest);
            userInterestsDtos.add(userInterestsDTO);
        }
        for(MatrimonyProfile profile: profiles){
            ProfileDto profileDto = assembler.convertToProfileDto(profile);
            profilesDto.add(profileDto);
        }
        UserProfilesResponse response = new UserProfilesResponse(profilesDto, userInterestsDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchProfiles")
    public ResponseEntity<UserProfilesResponse> getProfilesByName(@RequestParam String firstName,
                                                                  @RequestParam Long userId) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getAllProfilesExceptUser(userId);
        List<UserInterests> interests = userInterestService.getInterestsByUserId(userId);

        List<UserInterestsDTO> userInterestsDtos = new ArrayList<UserInterestsDTO>();
        List<ProfileDto> profilesDto = new ArrayList<ProfileDto>();

        for(UserInterests userInterest: interests){
            UserInterestsDTO userInterestsDTO = assembler.convertToUserInterestsDTO(userInterest);
            userInterestsDtos.add(userInterestsDTO);
        }
        for(MatrimonyProfile profile: profiles){
            if(profile.getFirstName().equals(firstName)){
                ProfileDto profileDto = assembler.convertToProfileDto(profile);
                profilesDto.add(profileDto);
            }
        }
        UserProfilesResponse response = new UserProfilesResponse(profilesDto, userInterestsDtos);
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/updateAcceptReject")
//    public ResponseEntity<?> updateInterestStatus(@RequestBody InterestUpdateRequest request) {
//        boolean result = userInterestsService.updateInterestStatus(request.getProfileId(),
//                request.getActionProfileId(),
//                request.getAction());
//        if (result) {
//            return ResponseEntity.ok().body("Action updated successfully");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to update action");
//        }
//    }

//    @GetMapping("/getAcceptedProfiles/{profileId}")
//    public ResponseEntity<List<Users>> getAcceptedProfiles(@PathVariable Long profileId) {
//        List<Users> acceptedProfiles = userInterestService.getAcceptedProfiles(profileId);
//        return ResponseEntity.ok(acceptedProfiles);
//    }
//
//    @GetMapping("/getRejectedProfiles/{profileId}")
//    public ResponseEntity<List<User>> getRejectedProfiles(@PathVariable Long profileId) {
//        List<User> rejectedProfiles = userInterestsService.getRejectedProfiles(profileId);
//        return ResponseEntity.ok(rejectedProfiles);
//    }

    @GetMapping("/getInterestedProfiles/{profileId}")
    public ResponseEntity<List<ProfileDto>> getInterestedProfiles(@PathVariable("profileId") Long profileId) {
        List<MatrimonyProfile> interestedProfiles = userInterestService.getInterestedProfiles(profileId);

        List<ProfileDto> profiles = new ArrayList<ProfileDto>();
        for(MatrimonyProfile prof: interestedProfiles){
            ProfileDto dto = assembler.convertToProfileDto(prof);
            profiles.add(dto);
        }
        return ResponseEntity.ok(profiles);
    }

    // Get interests for a user
//    @GetMapping("/{userId}/interested-in")
//    public List<UserInterests> getInterestedProfiles(@PathVariable Long userId) {
//        return userInterestRepository.findByUserId(userId);
//    }

    // Get users interested in a profile
    @GetMapping("/{userId}/interested-by")
    public List<UserInterests> getUsersInterestedIn(@PathVariable Long userId) {
        return userInterestRepository.findByInterestedInUserId(userId);
    }
}